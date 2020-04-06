public class Collaborator {


    public int returnSomething(int num){

        return num*2-3;

    }
    public int returnSomethingMore(int s, int m){

        return returnSomething(s)*returnSomething(m);

    }


}
