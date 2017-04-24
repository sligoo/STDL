import egg.Bloc;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.impl.TAMFactoryImpl;
import mg.egg.eggc.runtime.libjava.ISourceUnit;
import mg.egg.eggc.runtime.libjava.SourceUnit;
import mg.egg.eggc.runtime.libjava.problem.IProblem;
import mg.egg.eggc.runtime.libjava.problem.ProblemReporter;
import mg.egg.eggc.runtime.libjava.problem.ProblemRequestor;

import java.io.PrintWriter;

public class Main{

  public static void main (String[] args){

  try {
      ISourceUnit cu = new SourceUnit(args[0]);
      System.out.println("Fichier analyé : "+  args[0]);
      ProblemReporter prp = new ProblemReporter(cu);
      ProblemRequestor prq = new ProblemRequestor(true);
      Bloc bloc = new Bloc(prp);
      prq.beginReporting();
      bloc.set_eval(true);
      bloc.compile(cu);
      for(IProblem problem : prp.getAllProblems())
      	prq.acceptProblem(problem );
      prq.endReporting();
      System.out.println("AST :"+bloc.get_ast());
      if (bloc.get_ast().checkType()) {
          System.out.println( "Correctement typé." );

          String filename = args[0].substring(0, args[0].lastIndexOf('.')).concat(".tam");
          PrintWriter writer = new PrintWriter(filename, "UTF-8");

          // Test de l'allocation de mémoire
          bloc.get_ast().allocateMemory(Register.SB, 0);

          // Test génération de Code
          Fragment fragment = bloc.get_ast().getCode(new TAMFactoryImpl());
          fragment.add((new TAMFactoryImpl()).createHalt());
          writer.println(fragment);
          writer.close();
      } else {
	  System.out.println( "Mal typé." );
      }



      System.exit(0);
      }
    catch(Exception e){
      e.printStackTrace();
      System.exit(1);
      }
    }



}
