package nitesh.codsoft;
import java.util.*;
import java.util.concurrent.*;

class QuizQuestion {
    private String question;
    private List<String> options;
    private String correctOption;

    public QuizQuestion(String question, List<String> options, String correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectOption() {
        return correctOption;
    }
}

public class Quiz {
    private List<QuizQuestion> questions;
    private int score;
    private Scanner scanner;
    private Map<String, Integer> answerMap;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        score = 0;
        scanner = new Scanner(System.in);
        // Create answer map
        answerMap = new HashMap<>();
        answerMap.put("A", 0);
        answerMap.put("B", 1);
        answerMap.put("C", 2);
        answerMap.put("D", 3);
    }

    public void startQuiz() {
        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion currentQuestion = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((char) ('A' + j) + ". " + options.get(j));
            }

            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> future = executor.submit(() -> {
                System.out.print("Enter your choice (A/B/C/D): ");
                return scanner.nextLine().toUpperCase(); // Convert input to uppercase
            });

            try {
                String userChoice = future.get(30, TimeUnit.SECONDS); // Limit time to 30 seconds
                executor.shutdownNow(); // Shutdown the executor to interrupt the thread

                if (!answerMap.containsKey(userChoice)) {
                    System.out.println("Invalid choice. Skipping to next question.");
                    continue;
                }

                int choiceIndex = answerMap.get(userChoice);

                if (choiceIndex == answerMap.get(currentQuestion.getCorrectOption())) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Incorrect!");
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                System.out.println("Time's up! Skipping to next question.");
            }
        }

        System.out.println("Quiz completed!");
        System.out.println("Your score: " + score + "/" + questions.size());
    }

    public static void main(String[] args) {
        List<QuizQuestion> questions = new ArrayList<>();
        List<String> options1 = Arrays.asList("4", "5", "6", "7");
        QuizQuestion question1 = new QuizQuestion("What is 2 + 2?", options1, "A");
        List<String> options2 = Arrays.asList("12", "24", "36", "None");
        QuizQuestion question2 = new QuizQuestion("What is 3 * 4?", options2, "A");
        List<String> options3 = Arrays.asList("15", "5", "30", "None");
        QuizQuestion question3 = new QuizQuestion("What is 10 / 2?", options3, "B");

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);

        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }
}
