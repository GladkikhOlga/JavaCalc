import java.util.Scanner;

public class Main {
    public enum ResultType {ARAB, RIM};

    public static String calc(String input) {
        Integer result = 0;
        String resultString;
        int operand1 = 0;
        int operand2 = 0;
        ResultType resultType;
        String[] blocks = input.trim().split(" ");
        try {
            if (blocks.length < 2) throw new Exception("throws Exception //т.к. строка не является математической операцией");
            if (blocks.length < 3) throw new Exception("throws Exception //т.к. нет второго операнда");
            if (blocks.length > 3) throw new Exception("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            try {
                operand1 = Integer.parseInt(blocks[0]);
                operand2 = Integer.parseInt(blocks[2]);
                if (operand1 <= 0 || operand1 > 10 || operand2 <= 0 || operand2 > 10) {
                    throw new Exception ("throws Exception //т.к. калькулятор принимает на вход числа от 1 до 10 включительно");
                }

                System.out.println("both arabic");
                resultType = ResultType.ARAB;

            } catch (NumberFormatException exc) {
              String[] RimArray = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
              boolean isRim1 = false;
              boolean isRim2 = false;
              for (int i = 0; i < RimArray.length; i++) {
                if (blocks[0].equals(RimArray[i])) {
                    isRim1 = true;
                    operand1 = i + 1;
                  }
                if (blocks[2].equals(RimArray[i])) {
                    isRim2 = true;
                    operand2 = i + 1;
                  }
              }
              if (isRim1==false || isRim2==false) {
                  throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления или один из операндов не подходит под условия задачи");
              }
                resultType = ResultType.RIM;
            }
            result = calcOperation(operand1,operand2,blocks[1].charAt(0));
            if (resultType==ResultType.RIM) {
                if (result<=0){
                    throw new Exception("throws Exception //т.к. в римской системе нет отрицательных чисел");
                }
                resultString = RomanNumeral.values()[result-1].toString();
            } else {
                resultString = result.toString();
            }
        } catch (Exception e){
            resultString = e.getMessage();
        }
        return resultString;
    }

    public static void main(String[] args) {
        System.out.println("Введите выражение из двух римских или двух целых арабских цифр от 1 до 10 и знака" +
                " операции через пробел следующим образом: a + b");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        String result = calc(userInput);
        System.out.println(result);
        scan.close();
    }

    public static int calcOperation (int operand1, int operand2, char operation) throws Exception {
        int result;
        switch (operation) {
            case ('*'):
                result = operand1 * operand2;
                break;
            case ('/'):
                result = operand1 / operand2;
                break;
            case ('-'):
                result = operand1 - operand2;
                break;
            case ('+'):
                result = operand1 + operand2;
                break;
            default: throw new Exception("throws Exception //т.к. данная операция не предусмотрена калькулятором");
        }
        return result;
    }
}

