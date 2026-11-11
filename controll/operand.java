package controll;

public class operand {

   String op;

    public operand(String op) {
        this.op = op;
    }
    
    public boolean check (String op) {
        if (op.equals("addieren") || op.equals("subtrahieren") || op.equals("multiplizieren") || op.equals("dividieren")) {
            return true;
        } else {
            return false;
        }
    }

    public String getOp() {
        return op;
    }
    public void setOp(String op) {
        this.op = op;
    }

}
