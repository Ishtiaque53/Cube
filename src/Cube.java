import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Cube {
    static int count = 0;
    static LinkedList<State> openSet = new LinkedList<>();
    static LinkedList<State> closedSet = new LinkedList<>();
    static LinkedList<State> shortPath = new LinkedList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\nTop: ");
        int T1 = inputValue(scanner.next());
        int T2 = inputValue(scanner.next());
        int T3 = inputValue(scanner.next());
        int T4 = inputValue(scanner.next());

        System.out.println("\nFront: ");
        int D1 = inputValue(scanner.next());
        int D2 = inputValue(scanner.next());
        int D3 = inputValue(scanner.next());
        int D4 = inputValue(scanner.next());

        System.out.println("\nRight: ");
        int R3 = inputValue(scanner.next());
        int R1 = inputValue(scanner.next());
        int R4 = inputValue(scanner.next());
        int R2 = inputValue(scanner.next());

        System.out.println("\nBottom: ");
        int B1 = inputValue(scanner.next());
        int B2 = inputValue(scanner.next());
        int B3 = inputValue(scanner.next());
        int B4 = inputValue(scanner.next());

        System.out.println("\nBack: ");
        int U4 = inputValue(scanner.next());
        int U3 = inputValue(scanner.next());
        int U2 = inputValue(scanner.next());
        int U1 = inputValue(scanner.next());

        System.out.println("\nLeft: ");
        int L2 = inputValue(scanner.next());
        int L4 = inputValue(scanner.next());
        int L1 = inputValue(scanner.next());
        int L3 = inputValue(scanner.next());

        int[] startTop = {T1,T2,T3,T4};
        int[] startBottom = {B1,B2,B3,B4};
        int[] startLeft = {L1,L2,L3,L4};
        int[] startRight = {R1,R2,R3,R4};
        int[] startUp = {U1,U2,U3,U4};
        int[] startDown = {D1,D2,D3,D4};


        State initState = new State();
        initState.setState(startTop,startBottom,startLeft,startRight,startUp,startDown,0);
        openSet.add(initState);
        State currentState = closestState();

        while(true){
            if(openSet.size() == 0){
                System.out.println("\n\nUnreachable");
                break;
            }
            if(currentState.fitness == 0){
                System.out.println("\n\nReached");
                State pathState = currentState;
                while (pathState!=null){
                    shortPath.addFirst(pathState);
                    pathState = pathState.previousState;
                }
                break;
            }
            closedSet.add(currentState);
            openSet.remove(currentState);
            createState(currentState);
            if(openSet.size()!=0){
                currentState = closestState();
            }
        }
        System.out.println("\nShortest distance to solution: " + (shortPath.size()-1));

    }

    static  public void addToOpenSet(State state, State previousState) {
        if (!checkSets(state)) {
            state.previousState = previousState;
            openSet.add(state);
            count++;
        }
    }


    static public boolean checkSets(State state){
        boolean inOpenSet = false;
        boolean inClosedSet = false;
        for (State compareState : openSet) {
            if (Arrays.equals(compareState.top, state.top) && Arrays.equals(compareState.bottom, state.bottom) && Arrays.equals(compareState.left, state.left) && Arrays.equals(compareState.right, state.right) && Arrays.equals(compareState.up, state.up) && Arrays.equals(compareState.down, state.down)) {
                inOpenSet = true;
                //System.out.println("Matched in open set");
            }
        }
        for (State compareState : closedSet) {
            if (Arrays.equals(compareState.top, state.top) && Arrays.equals(compareState.bottom, state.bottom) && Arrays.equals(compareState.left, state.left) && Arrays.equals(compareState.right, state.right) && Arrays.equals(compareState.up, state.up) && Arrays.equals(compareState.down, state.down)) {
                inClosedSet = true;
                //System.out.println("Matched in closed set");
            }
        }
        return inClosedSet || inOpenSet;
    }

    static public State closestState(){
        State state = openSet.element();
        for (State compareState : openSet) {
            if (compareState.fitness < state.fitness) {
                state = compareState;
                return state;
            }
        }
        return state;
    }

    static public int inputValue(String a){
        int value=1;
        switch (a){
            case "w","W":
                value=1;
                break;
            case "g","G":
                value=2;
                break;
            case "r","R":
                value=3;
                break;
            case "y","Y":
                value=4;
                break;
            case "o","O":
                value=5;
                break;
            case "b","B":
                value=6;
                break;
            default:
                break;
        }
        return value;
    }

    static public void createState(State state){
        State newState1 = new State();
        State newState2 = new State();
        State newState3 = new State();
        State newState4 = new State();
        State newState5 = new State();
        State newState6 = new State();
        State newState7 = new State();
        State newState8 = new State();
        State newState9 = new State();
        State newState10 = new State();
        State newState11 = new State();
        State newState12 = new State();
        newState1.setState(Arrays.copyOf(state.top,state.top.length),Arrays.copyOf(state.bottom,state.bottom.length),Arrays.copyOf(state.left,state.left.length),Arrays.copyOf(state.right,state.right.length),Arrays.copyOf(state.up,state.up.length),Arrays.copyOf(state.down,state.down.length), state.cost);
        newState2.setState(Arrays.copyOf(state.top,state.top.length),Arrays.copyOf(state.bottom,state.bottom.length),Arrays.copyOf(state.left,state.left.length),Arrays.copyOf(state.right,state.right.length),Arrays.copyOf(state.up,state.up.length),Arrays.copyOf(state.down,state.down.length), state.cost);
        newState3.setState(Arrays.copyOf(state.top,state.top.length),Arrays.copyOf(state.bottom,state.bottom.length),Arrays.copyOf(state.left,state.left.length),Arrays.copyOf(state.right,state.right.length),Arrays.copyOf(state.up,state.up.length),Arrays.copyOf(state.down,state.down.length), state.cost);
        newState4.setState(Arrays.copyOf(state.top,state.top.length),Arrays.copyOf(state.bottom,state.bottom.length),Arrays.copyOf(state.left,state.left.length),Arrays.copyOf(state.right,state.right.length),Arrays.copyOf(state.up,state.up.length),Arrays.copyOf(state.down,state.down.length), state.cost);
        newState5.setState(Arrays.copyOf(state.top,state.top.length),Arrays.copyOf(state.bottom,state.bottom.length),Arrays.copyOf(state.left,state.left.length),Arrays.copyOf(state.right,state.right.length),Arrays.copyOf(state.up,state.up.length),Arrays.copyOf(state.down,state.down.length), state.cost);
        newState6.setState(Arrays.copyOf(state.top,state.top.length),Arrays.copyOf(state.bottom,state.bottom.length),Arrays.copyOf(state.left,state.left.length),Arrays.copyOf(state.right,state.right.length),Arrays.copyOf(state.up,state.up.length),Arrays.copyOf(state.down,state.down.length), state.cost);
        newState7.setState(Arrays.copyOf(state.top,state.top.length),Arrays.copyOf(state.bottom,state.bottom.length),Arrays.copyOf(state.left,state.left.length),Arrays.copyOf(state.right,state.right.length),Arrays.copyOf(state.up,state.up.length),Arrays.copyOf(state.down,state.down.length), state.cost);
        newState8.setState(Arrays.copyOf(state.top,state.top.length),Arrays.copyOf(state.bottom,state.bottom.length),Arrays.copyOf(state.left,state.left.length),Arrays.copyOf(state.right,state.right.length),Arrays.copyOf(state.up,state.up.length),Arrays.copyOf(state.down,state.down.length), state.cost);
        newState9.setState(Arrays.copyOf(state.top,state.top.length),Arrays.copyOf(state.bottom,state.bottom.length),Arrays.copyOf(state.left,state.left.length),Arrays.copyOf(state.right,state.right.length),Arrays.copyOf(state.up,state.up.length),Arrays.copyOf(state.down,state.down.length), state.cost);
        newState10.setState(Arrays.copyOf(state.top,state.top.length),Arrays.copyOf(state.bottom,state.bottom.length),Arrays.copyOf(state.left,state.left.length),Arrays.copyOf(state.right,state.right.length),Arrays.copyOf(state.up,state.up.length),Arrays.copyOf(state.down,state.down.length), state.cost);
        newState11.setState(Arrays.copyOf(state.top,state.top.length),Arrays.copyOf(state.bottom,state.bottom.length),Arrays.copyOf(state.left,state.left.length),Arrays.copyOf(state.right,state.right.length),Arrays.copyOf(state.up,state.up.length),Arrays.copyOf(state.down,state.down.length), state.cost);
        newState12.setState(Arrays.copyOf(state.top,state.top.length),Arrays.copyOf(state.bottom,state.bottom.length),Arrays.copyOf(state.left,state.left.length),Arrays.copyOf(state.right,state.right.length),Arrays.copyOf(state.up,state.up.length),Arrays.copyOf(state.down,state.down.length), state.cost);

        newState1.moveUp(0,1);
        newState2.moveUp(1,2);
        newState3.moveDown(0,3);
        newState4.moveDown(1,4);
        newState5.moveLeft(0,5);
        newState6.moveLeft(2,6);
        newState7.moveRight(0,7);
        newState8.moveRight(2,8);
        newState9.rotateRight(0,9);
        newState10.rotateRight(1,10);
        newState11.rotateLeft(0,11);
        newState12.rotateLeft(1,12);

        addToOpenSet(newState1, state);
        addToOpenSet(newState2,state);
        addToOpenSet(newState3,state);
        addToOpenSet(newState4,state);
        addToOpenSet(newState5,state);
        addToOpenSet(newState6,state);
        addToOpenSet(newState7,state);
        addToOpenSet(newState8,state);
        addToOpenSet(newState9,state);
        addToOpenSet(newState10,state);
        addToOpenSet(newState11,state);
        addToOpenSet(newState12,state);

    }
}
