public class HealthRecord {
    private double weight;
    private double height;
    private int age;
    private double bmi;
    private String category;
    private String advice;

    public HealthRecord(double weight, double height, int age) {
        this.weight = weight;
        this.height = height;
        this.age = age;

        this.bmi = BMICalculator.calculateBMI(weight, height);
        this.category = BMICalculator.getCategory(bmi);
        this.advice = BMICalculator.getAdvice(category);
    }

    public String getDetails() {
        return "Weight: " + weight + " kg\n" +
               "Height: " + height + " m\n" +
               "Age: " + age + "\n" +
               "BMI: " + bmi + "\n" +
               "Category: " + category + "\n" +
               "Advice: " + advice + "\n" +
               "Ideal Weight: " + BMICalculator.idealWeight(height) + " kg\n";
    }
}

