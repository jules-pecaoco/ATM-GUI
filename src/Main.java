import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main{
    //This is new Changes 1.0
    static List <String> list = new ArrayList<>();
    static JTextField user = new JTextField();
    static JTextField pin = new JPasswordField();
    static JTextField con = new JPasswordField();
    static JTextField name = new JTextField();
    static JTextField pass = new JPasswordField();

    public static int trans,dep,with,sav=0,cur=0,rerun, choice,accT,tranOption,error, histnum=0;

    static String [] option = {"Savings", "Current"};
    static String [] transaction = {"Deposit", "Withdraw", "Fund Transfer", "Check Balance", "History", "Exit"};
    static Object [] transac = {"Savings to Current", "Current to Savings"};

    static void inputAcc(){
        try{
            System.out.println(con.getText());
            while (true){
                Object[] sage = {
                        "Username", name,
                        "Password", pass,
                };
                int optio = JOptionPane.showConfirmDialog(null,sage,"Input Credentials",JOptionPane.YES_NO_OPTION);
                if (optio == JOptionPane.OK_OPTION){
                    if (name.getText().equals((user.getText()))&&pass.getText().equals(con.getText())){
                        myATM();
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null,"Input does not match username and password","Warning",JOptionPane.WARNING_MESSAGE);
                        if (!pass.getText().equals(con.getText())) {
                            error++;
                            if (error>0){
                                if (error==3){
                                    JOptionPane.showMessageDialog(null,"Card is now Confiscated");
                                    break;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Number of tries left: "+ (3-error),"Password Invalid",JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"...........");
                    break;
                }
            }

        }catch (Exception numberFormatException){
            reRun();
        }
    }
    static void transAction(){
        trans=0;
        trans = Integer.parseInt(JOptionPane.showInputDialog(null, "Input Amount to transfer"));
        tranOption = JOptionPane.showOptionDialog(null,"Select Account Type","Accounts", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,transac,transac[0]);
        if(tranOption==0){
            if(trans>sav){
                JOptionPane.showMessageDialog(null,"Cannot Transfer more than Savings", "Warning", JOptionPane.WARNING_MESSAGE);
                reRun();
            }else{
                sav -= trans;
                cur += trans;
                String[] mytranSavings = {
                        "\nTransaction Number: ", String.valueOf(histnum),
                        "\nAmount Transferred to Current: ", String.valueOf(trans),
                        "\nCurrent Balance: ", String.valueOf(cur),
                        "\nSavings Balance: ", String.valueOf(sav),
                        "\n"
                };
                JOptionPane.showMessageDialog(null,"New Savings Balance: "+ sav+ "\nNew Current Balance: " + cur);
                System.out.println(Arrays.toString(mytranSavings).replaceAll("^\\[|]$|,+",""));
                list.add(Arrays.toString(mytranSavings).replaceAll("^\\[|]$|,+",""));
                reRun();
            }
        } else if (tranOption==1) {
            if(trans>cur){
                JOptionPane.showMessageDialog(null,"Cannot Transfer more than Current", "Warning", JOptionPane.WARNING_MESSAGE);
                reRun();

            }else{
                sav += trans;
                cur -= trans;
                String[] mytranCurrent = {
                        "\nTransaction Number", String.valueOf(histnum),
                        "\nAmount Transferred to Savings: ", String.valueOf(trans),
                        "\nSavings Balance: ", String.valueOf(cur),
                        "\nCurrent Balance: ", String.valueOf(sav),
                        "\n"
                };
                JOptionPane.showMessageDialog(null,"New Savings Balance: "+ sav+ "\nNew Current Balance: " + cur);
                System.out.println(Arrays.toString(mytranCurrent).replaceAll("^\\[|]$|,+",""));
                list.add(Arrays.toString(mytranCurrent).replaceAll("^\\[|]$|,+",""));
                reRun();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Sudden Exit...");}
    }

    static void accountType(){
        try{
            accT = JOptionPane.showOptionDialog(null,"Select Account Type","Accounts", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
            if (accT==0){
                if (choice==0){
                    sav += dep;
                    String [] myhistSavings = {
                            "\nTransaction Number", String.valueOf(histnum),
                            "\nAmount Deposited: ", String.valueOf(dep),
                            "\nSavings Balance: ", String.valueOf(sav),
                            "\n"
                    };
                    JOptionPane.showMessageDialog(null,"Savings Balance: "+ sav);
                    System.out.println(Arrays.toString(myhistSavings).replaceAll("^\\[|]$|,+",""));
                    list.add(Arrays.toString(myhistSavings).replaceAll("^\\[|]$|,+",""));
                    reRun();
                } else if (choice==1) {
                    if (with>sav){
                        JOptionPane.showMessageDialog(null,"Cannot Withdraw More than Savings","Withdraw" ,JOptionPane.WARNING_MESSAGE);
                        reRun();
                    } else if ((sav*.75)<with) {
                        JOptionPane.showMessageDialog(null,"Cannot Withdraw More than 75% of Savings","Withdraw" ,JOptionPane.WARNING_MESSAGE);
                        reRun();
                    }else{
                        sav -= with;
                        String [] myhistSavings = {
                                "\nTransaction Number", String.valueOf(histnum),
                                "\nAmount Withdrawn: ", String.valueOf(with),
                                "\nSavings Balance: ", String.valueOf(sav),
                                "\n"
                        };
                        JOptionPane.showMessageDialog(null,"Savings Balance: "+ sav);
                        System.out.println(Arrays.toString(myhistSavings).replaceAll("^\\[|]$|,+",""));
                        list.add(Arrays.toString(myhistSavings).replaceAll("^\\[|]$|,+",""));
                        reRun();
                    }
                }
            } else if (accT==1) {
                if (choice==0){
                    cur += dep;
                    String [] myhistCurrent = {
                            "\nTransaction Number", String.valueOf(histnum),
                            "\nAmount Deposited: ", String.valueOf(dep),
                            "\nCurrent Balance: ", String.valueOf(cur),
                            "\n"
                    };
                    JOptionPane.showMessageDialog(null,"Current Balance: "+ cur);
                    System.out.println(Arrays.toString(myhistCurrent).replaceAll("^\\[|]$|,+",""));
                    list.add(Arrays.toString(myhistCurrent).replaceAll("^\\[|]$|,+",""));
                    reRun();

                }else if (choice==1) {
                    if (with>cur){
                        JOptionPane.showMessageDialog(null,"Cannot Withdraw More than Current","Withdraw" ,JOptionPane.WARNING_MESSAGE);
                        reRun();
                    } else if ((cur*.75)<with) {
                        JOptionPane.showMessageDialog(null,"Cannot Withdraw More than 75% of Current","Withdraw" ,JOptionPane.WARNING_MESSAGE);
                        reRun();
                    }else{
                        cur -= with;
                        String [] myhistCurrent = {
                                "\nTransaction Number", String.valueOf(histnum),
                                "\nAmount Withdrawn: ", String.valueOf(with),
                                "\nCurrent Balance: ", String.valueOf(cur),
                                "\n"
                        };
                        JOptionPane.showMessageDialog(null,"Current Balance: "+ cur);
                        System.out.println(Arrays.toString(myhistCurrent).replaceAll("^\\[|]$|,+",""));
                        list.add(Arrays.toString(myhistCurrent).replaceAll("^\\[|]$|,+",""));
                        reRun();

                    }
                }
            }

        }catch (Exception e){
            reRun();
        }
    }
    static void reRun(){
        rerun = JOptionPane.showConfirmDialog(null,"Do you want to another Transaction?", "Rerun Program",JOptionPane.YES_NO_OPTION);
        if (rerun==JOptionPane.YES_OPTION){
            myATM();
        }else{
            JOptionPane.showMessageDialog(null,"Thank Your For Using the Program");
        }
    }
    static void myATM(){
        dep=0;
        with=0;
        trans=0;
        try{
            choice = JOptionPane.showOptionDialog(null,"Select Transaction","Menu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,transaction,transaction[0]);
            switch (choice) {
                case  0 -> {
                    ++histnum;

                    while(true){
                        dep = Integer.parseInt(JOptionPane.showInputDialog(null, "Input amount to be Deposit"));
                        if (dep<200){
                            JOptionPane.showMessageDialog(null,"Cannot Deposit Less than 200","", JOptionPane.WARNING_MESSAGE);
                        }else if (dep%100!=0) {
                            JOptionPane.showMessageDialog(null,"Cannot Deposit Less than Multiple of 100","", JOptionPane.WARNING_MESSAGE);
                        }else {
                            accountType();
                            break;
                        }
                    }
                }case 1 -> {
                    ++histnum;

                    while(true){
                        with = Integer.parseInt(JOptionPane.showInputDialog(null, "Input amount to be Withdraw"));
                        if (with<200){
                            JOptionPane.showMessageDialog(null,"Cannot Withdraw Less than 200","", JOptionPane.WARNING_MESSAGE);
                        }else if (with%100!=0) {
                            JOptionPane.showMessageDialog(null,"Cannot Withdraw Less than Multiple of 100","", JOptionPane.WARNING_MESSAGE);
                        }else {
                            accountType();
                            break;
                        }
                    }
                }
                case 2 ->{++histnum; transAction();}
                case 3 -> {
                    JOptionPane.showMessageDialog(null,"Savings Balance: "+ sav+ "\nCurrent Balance: " + cur);
                    reRun();

                }case 4 -> {
                    System.out.println(list.toString().replaceAll("^\\[|]$|,+", ""));
                    reRun();
                }case 5 -> JOptionPane.showMessageDialog(null,"Thank You for Using the ATM Machine");
                default -> {
                    JOptionPane.showMessageDialog(null,"Wrong Input", "Input Error", JOptionPane.WARNING_MESSAGE);
                    myATM();
                }
            }
        }catch (Exception e){
            reRun();
        }
    }
    public static void main(String[] args) {

        while (true) {
            Object[] message = {
                    "Enter Username", user,
                    "Enter Password", pin,
                    "Confirm Password", con
            };

            int opt = JOptionPane.showConfirmDialog(null, message, "Create Username and Password", JOptionPane.OK_CANCEL_OPTION);
            if (opt == JOptionPane.OK_OPTION) {
                int countWords = pin.getText().length();
                if ((pin.getText()).isBlank()||(user.getText().isBlank())) {
                    JOptionPane.showMessageDialog(null, "Username or Password is Empty", "Warning", JOptionPane.WARNING_MESSAGE);

                }else if(!user.getText().matches("[a-z]+")){
                    JOptionPane.showMessageDialog(null,"Username cannot contain numbers and must be of lowercase" , "Warning", JOptionPane.WARNING_MESSAGE);

                } else if (pin.getText().matches("[0-9]+")) {
                    if (!pin.getText().equals(con.getText())) {
                        JOptionPane.showMessageDialog(null, "Password does not match","Warning",JOptionPane.WARNING_MESSAGE);
                    } else if (countWords != 6) {
                        JOptionPane.showMessageDialog(null, "Password must be of 6 character","Warning",JOptionPane.WARNING_MESSAGE);
                    } else {
                        System.out.println("Loading...");
                        JOptionPane.showMessageDialog(null, "Welcome To ATM Machine");
                        inputAcc();
                        break;
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Only Input numbers in Password","Warning",JOptionPane.WARNING_MESSAGE);

                }

            } else {
                JOptionPane.showMessageDialog(null, "Login Cancelled");
                break;

            }
        }
    }
}




