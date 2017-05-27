/**
 * Created by icondor on 19/04/2017.
 */
public class MathOperationsBusinessLogic {

    public static double getResultValue(String sOp, int nr1, int nr2) {
        System.out.println("enter in getResultValue with args: "+sOp+":"+nr1+":"+nr2);
        double resultValue = 0;
        switch (sOp) {
            case "1":
                resultValue = nr1 + nr2;
                break;
            case "2":
                resultValue = nr1 - nr2;
                break;
            case "3":
                resultValue = nr1 * nr2;
                break;
            case "4":
                resultValue = (double) nr1 / nr2;
                break;
        }
        System.out.println("returning value from getResultValue:"+resultValue);
        return resultValue;
    }
}
