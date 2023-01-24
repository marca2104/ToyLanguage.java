package view;

import controller.Controller;
import exceptions.MyException;
import model.*;
import repository.IRepository;
import repository.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;

public class View {
    public void start() {
        boolean done = false;
        while (!done) {
            try {
                showMenu();
                Scanner readOption = new Scanner(System.in);
                int option = readOption.nextInt();
                if (option == 0) {
                    done = true;
                } else if (option == 1)
                {
                    runProgram1();
                } else if (option == 2) {
                    runProgram2();
                } else if (option == 3)
                {
                    runProgram3();
                } else if (option == 4){
                    runProgram4();
                }else if (option == 5){
                    runProgram5();}
                else if (option == 6){
                    runProgram6();}
                else if (option == 7){
                    runProgram7();}
                else if (option == 8){
                    runProgram8();}
                else if (option == 9){
                    runProgram9();}
                else if (option == 10){
                    runProgram10();}
                else
                {
                    System.out.println("Invalid input!");
                }
            } catch (Exception exception) {
                System.out.println("\u001B[31m" + exception.getMessage() + "\u001B[0m");
            }
        }
    }

    private void showMenu() {
        System.out.println("MENU: ");
        System.out.println("0. Exit.");
        System.out.println("1. Run the first program: \n\tint v;\n\tv=2;\n\tPrint(v)");
        System.out.println("2. Run the second program: \n\tint a;\n\tint b;\n\ta=2+3*5;\n\tb=a+1;\n\tPrint(b)");
        System.out.println("3. Run the third program: \n\tbool a;\n\tint v;\n\ta=true;\n\t(If a Then v=2 Else v=3);\n\tPrint(v)");
        System.out.println("4. Run the fourth program:");
        System.out.println("5. Run the fifth program:");
        System.out.println("6. Run the sixth program:");
        System.out.println("7. Run the seventh program:");
        System.out.println("8. Run the eighth program:");
        System.out.println("9. Run the ninth program:");
        System.out.println("10. Run the tenth program:");
        System.out.println("Choose an option: ");
    }

    private void runProgram1() throws MyException, IOException, InterruptedException {
        IStmt ex1 = new CompStmt(new VarDecIStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                        new PrintStmt(new VarExp("v"))));
        runStatement(ex1);
    }

    private void runProgram2() throws MyException, IOException, InterruptedException {
        IStmt ex2 = new CompStmt(new VarDecIStmt("a",new IntType()),
                new CompStmt(new VarDecIStmt("b",new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
                                ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new
                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        runStatement(ex2);
    }

    private void runProgram3() throws MyException, IOException, InterruptedException {
        IStmt ex3 = new CompStmt(new VarDecIStmt("a", new BoolType()),
                new CompStmt(new VarDecIStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"),
                                        new AssignStmt("v", new ValueExp(new IntValue(2))),
                                        new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                        new PrintStmt(new VarExp("v"))))));
        runStatement(ex3);
    }

    private void runProgram4() throws MyException, IOException, InterruptedException {
        IStmt ex4 = new CompStmt(new VarDecIStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                        new CompStmt(new OpenReadFile(new VarExp("varf")),
                                new CompStmt(new VarDecIStmt("varc", new IntType()),
                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseReadFile(new VarExp("varf"))))))))));
        runStatement(ex4);

    }

    private void runProgram5() throws MyException, IOException, InterruptedException {
        IStmt ex5 = new CompStmt(new VarDecIStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStatement("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDecIStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStatement("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
        runStatement(ex5);
    }

    private void runProgram6() throws MyException, IOException, InterruptedException {
        IStmt ex6 = new CompStmt(new VarDecIStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStatement("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDecIStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStatement("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new ReadHeapExpression(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+',new ReadHeapExpression(new ReadHeapExpression(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
        runStatement(ex6);
    }

    private void runProgram7() throws MyException, IOException, InterruptedException {
        IStmt ex7 = new CompStmt(new VarDecIStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStatement("v", new ValueExp(new IntValue(20))),
                        new CompStmt( new PrintStmt(new ReadHeapExpression(new VarExp("v"))),
                                new CompStmt(new WriteHeapStatement("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp('+', new ReadHeapExpression(new VarExp("v")), new ValueExp(new IntValue(5))))))));
        runStatement(ex7);
    }

    private void runProgram8() throws MyException, IOException, InterruptedException{
        IStmt ex8 = new CompStmt(new VarDecIStmt("v", new IntType()),
                new CompStmt(new VarDecIStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStatement("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStatement("a", new ValueExp(new IntValue(30))),
                                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExpression(new VarExp("a"))))))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExpression(new VarExp("a")))))))));
        runStatement(ex8);
    }

    private void runProgram9() throws MyException, IOException, InterruptedException{
        IStmt ex9 = new CompStmt(new VarDecIStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStatement("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDecIStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewStatement("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new ReadHeapExpression(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+',new ReadHeapExpression(new ReadHeapExpression(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
        runStatement(ex9);
    }

    private void runProgram10() throws MyException, IOException, InterruptedException {
        IStmt ex10 = new CompStmt(new VarDecIStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStatement("v", new ValueExp(new IntValue(20))),
                        new CompStmt( new PrintStmt(new ReadHeapExpression(new VarExp("v"))),
                                new CompStmt(new WriteHeapStatement("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp('+', new ReadHeapExpression(new VarExp("v")), new ValueExp(new IntValue(5))))))));
        runStatement(ex10);
    }


        private void runStatement(IStmt statement) throws MyException, IOException, InterruptedException {
        MyIStack<IStmt> executionStack = new MyStack<>();
        MyIDictionary<String, Value> symbolTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyIHeap heap = new MyHeap();

        PrgState state = new PrgState(executionStack, symbolTable, output, fileTable, heap, statement);

        IRepository repository = new Repository(state, "log.txt");
        Controller controller = new Controller(repository);

        System.out.println("Do you want to display the steps?[Y/n]");
        Scanner readOption = new Scanner(System.in);
        String option = readOption.next();
        controller.setDisplayFlag(Objects.equals(option, "Y"));

        controller.allStep();
        System.out.println("Result is" + state.getOut().toString().replace('[', ' ').replace(']', ' '));
    }

}
