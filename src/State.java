import java.lang.Math;

public class State {
    public int[] top = {0,0,0,0};
    public int[] bottom = {0,0,0,0};
    public int[] left = {0,0,0,0};
    public int[] right = {0,0,0,0};
    public int[] up = {0,0,0,0};
    public int[] down = {0,0,0,0};

    public int[] finalTop = {2,2,2,2};
    public int[] finalBottom = {4,4,4,4};
    public int[] finalLeft = {3,3,3,3};
    public int[] finalRight = {5,5,5,5};
    public int[] finalUp = {6,6,6,6};
    public int[] finalDown = {1,1,1,1};

    public int fitness;
    public int cost = 0;

    State previousState = null;


    public void calculateFitness(){
        this.fitness = Math.abs(distance(finalTop) - distance(top)) + Math.abs(distance(finalBottom) - distance(bottom)) +Math.abs(distance(finalLeft) - distance(left)) +Math.abs(distance(finalRight) - distance(right)) +Math.abs(distance(finalUp) - distance(up)) +Math.abs(distance(finalDown) - distance(down));
        if(this.fitness==0){
            this.cost=0;
        }
        this.fitness = this.fitness + this.cost;
    }
    
    public void setState(int[] top, int[] bottom, int[] left, int[] right, int[] up, int[] down , int cost){
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.cost = cost;
        calculateFitness();
    }


    public int distance(int[] array){
        int dist = array[0]+array[1]+array[2]+array[3];
        return dist;
    }

    public void moveUp(int index,int costIncrement){
        int a,b;
        a = this.top[index];
        this.top[index] = this.down[index];
        this.down[index] = this.bottom[index];
        this.bottom[index] = this.up[index];
        this.up[index] = a;

        b = this.top[index+2];
        this.top[index+2] = this.down[index+2];
        this.down[index+2] = this.bottom[index+2];
        this.bottom[index+2] = this.up[index+2];
        this.up[index+2] = b;

        if(index==0){
            a=this.left[0];
            b=this.left[2];
            this.left[0]=this.left[1];
            this.left[2]=a;
            this.left[1]=this.left[3];
            this.left[3]=b;
        }
        else {
            a=this.right[0];
            this.right[0]=this.right[2];
            this.right[2]=this.right[3];
            this.right[3]=this.right[1];
            this.right[1]=a;
        }
        this.cost = this.cost + costIncrement;
        calculateFitness();
    }

    public void moveDown(int index,int costIncrement){
        int a,b;
        a = this.top[index];
        this.top[index] = this.up[index];
        this.up[index] = this.bottom[index];
        this.bottom[index] = this.down[index];
        this.down[index] = a;

        b = this.top[index+2];
        this.top[index+2] = this.up[index+2];
        this.up[index+2] = this.bottom[index+2];
        this.bottom[index+2] = this.down[index+2];
        this.down[index+2] = b;
        if(index==0){
            a=this.left[0];
            b=this.left[2];
            this.left[2]=this.left[3];
            this.left[0]=b;
            this.left[3]=this.left[1];
            this.left[1]=a;
        }
        else {
            a=this.right[0];
            this.right[0]=this.right[1];
            this.right[1]=this.right[3];
            this.right[3]=this.right[2];
            this.right[2]=a;
        }
        this.cost = this.cost + costIncrement;
        calculateFitness();
    }

    public void moveRight(int index,int costIncrement){
        int a,b;
        a=this.top[index];
        this.top[index] = this.left[index];
        this.left[index] = this.bottom[index];
        this.bottom[index] = this.right[index];
        this.right[index] = a;

        b=this.top[index+1];
        this.top[index+1] = this.left[index+1];
        this.left[index+1] = this.bottom[index+1];
        this.bottom[index+1] = this.right[index+1];
        this.right[index+1] = b;

        if(index==0){
            a=this.up[0];
            b=this.up[2];
            this.up[0]=this.up[1];
            this.up[1]=this.up[3];
            this.up[2]=a;
            this.up[3]=b;
        }
        else {
            a=this.down[0];
            b=this.down[2];
            this.down[2]=this.down[3];
            this.down[0]=b;
            this.down[3]=this.down[1];
            this.down[1]=a;
        }
        this.cost = this.cost + costIncrement;
        calculateFitness();
    }

    public void moveLeft(int index,int costIncrement){
        int a,b;
        a=this.top[index];
        this.top[index] = this.right[index];
        this.right[index] = this.bottom[index];
        this.bottom[index] = this.left[index];
        this.left[index] = a;

        b=this.top[index+1];
        this.top[index+1] = this.right[index+1];
        this.right[index+1] = this.bottom[index+1];
        this.bottom[index+1] = this.left[index+1];
        this.left[index+1] = b;
        if(index==0){
            a=this.up[0];
            //b=this.up[2];
            this.up[0]=this.up[2];
            this.up[2]=this.up[3];
            this.up[3]=this.up[1];
            this.up[1]=a;
        }
        else {
            a=this.down[0];
            b=this.down[2];
            this.down[0]=this.down[1];
            this.down[2]=a;
            this.down[1]=this.down[3];
            this.down[3]=b;
        }
        this.cost = this.cost + costIncrement;
        calculateFitness();
    }

    public void rotateRight(int index,int costIncrement){
        int a,b;
        if (index==0){
            a= this.left[index];
            this.left[index]=this.down[index+2];
            this.down[index+2]= this.right[(index+3)%4];
            this.right[(index+3)%4] = this.up[index+1];
            this.up[index+1]=a;
            b= this.left[index+2];
            this.left[index+2] = this.down[(index+3)%4];
            this.down[(index+3)%4] = this.right[index+1];
            this.right[index+1] = this.up[index];
            this.up[index] = b;
        }
        else{
            a = this.left[index];
            this.left[index] = this.down[(index+3)%4];
            this.down[(index+3)%4] = this.right[index+1];
            this.right[index+1] = this.up[index+2];
            this.up[index+2] = a;
            b= this.left[index+2];
            this.left[index+2] = this.down[index];
            this.down[index] = this.right[(index+3)%4];
            this.right[(index+3)%4] = this.up[index+1];
            this.up[index+1] = b;

        }
        if(index==1){
            a=this.top[0];
            b=this.top[2];
            this.top[2]=this.top[3];
            this.top[0]=b;
            this.top[3]=this.top[1];
            this.top[1]=a;
        }
        else {
            a=this.bottom[0];
            b=this.bottom[2];
            this.bottom[2]=this.bottom[3];
            this.bottom[0]=b;
            this.bottom[3]=this.bottom[1];
            this.bottom[1]=a;
        }
        this.cost = this.cost + costIncrement;
        calculateFitness();
    }

    public void rotateLeft(int index,int costIncrement){
        int a,b;
        if (index==0){
            a= this.left[index];
            this.left[index]=this.up[index+1];
            this.up[index+1]=this.right[(index+3)%4];
            this.right[(index+3)%4] = this.down[index+2];
            this.down[index+2]= a;
            b= this.left[index+2];
            this.left[index+2] =  this.up[index];
            this.up[index] = this.right[index+1];
            this.right[index+1] = this.down[(index+3)%4];
            this.down[(index+3)%4] = b;
        }
        else{
            a = this.left[index];
            this.left[index] = this.up[index+2];
            this.up[index+2] = this.right[index+1];
            this.right[index+1] = this.down[(index+3)%4];
            this.down[(index+3)%4] = a;
            b= this.left[index+2];
            this.left[index+2] = this.up[index+1];
            this.up[index+1] = this.right[(index+3)%4];
            this.right[(index+3)%4] = this.down[index];
            this.down[index] = b;

        }


        if(index==1){
            a=this.top[0];
            b=this.top[2];
            this.top[0]=this.top[1];
            this.top[2]=a;
            this.top[1]=this.top[3];
            this.top[3]=b;
        }
        else {
            a=this.bottom[0];
            b=this.bottom[2];
            this.bottom[0]=this.bottom[1];
            this.bottom[2]=a;
            this.bottom[1]=this.bottom[3];
            this.bottom[3]=b;
        }
        this.cost = this.cost + costIncrement;
        calculateFitness();
    }
}
