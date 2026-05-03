public class BMICalculator {

    public static double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }

    public static String getCategory(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }

    public static String getAdvice(String category) {
        switch (category) {
            case "Underweight": return "Eat more nutritious food.";
            case "Normal": return "Keep maintaining your lifestyle.";
            case "Overweight": return "Exercise more and eat healthy.";
            default: return "Consult a doctor and exercise regularly.";
        }
    }

    public static double idealWeight(double height) {
        return 22 * height * height;
    }
}

