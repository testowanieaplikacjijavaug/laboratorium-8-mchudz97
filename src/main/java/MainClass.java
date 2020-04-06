import java.util.ArrayList;
import java.util.List;

public class MainClass {


    private List<Collaborator> colls;


    public MainClass(){

        this.colls = new ArrayList<Collaborator>(0);

    }


    public void putColl(Collaborator coll){

        if(coll == null ) throw new IllegalArgumentException("NULL");

        this.colls.add(coll);


    }

    public Collaborator getColl(int index){

        return this.colls.get(index);

    }

    public void setColls(List<Collaborator> colls){

        this.colls = colls;

    }

    public int getIndex(Collaborator collaborator){

        return this.colls.indexOf(collaborator);

    }

    public int somethingFromCollaborator(Collaborator col, int v1, int v2){

        return col.returnSomethingMore(v1, v2);

    }




}
