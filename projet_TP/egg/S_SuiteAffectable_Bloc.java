package egg;
import java.util.*;
import fr.n7.stl.block.ast.*;
import fr.n7.stl.block.ast.impl.*;
import fr.n7.stl.util.*;
import mg.egg.eggc.runtime.libjava.lex.*;
import mg.egg.eggc.runtime.libjava.*;
import mg.egg.eggc.runtime.libjava.messages.*;
import mg.egg.eggc.runtime.libjava.problem.IProblem;
import java.util.Vector;
public class S_SuiteAffectable_Bloc {
LEX_Bloc scanner;
  S_SuiteAffectable_Bloc() {
	}
  S_SuiteAffectable_Bloc(LEX_Bloc scanner, boolean eval) {
	this.scanner = scanner;
	this.att_eval = eval;
	this.att_scanner = scanner;
	}
int [] sync= new int[0];
  BlockFactory att_factory;
  SymbolTable att_tds;
  boolean att_eval;
  Assignable att_ast;
  LEX_Bloc att_scanner;
  Assignable att_support;
  private void regle12() throws Exception {

	//declaration
	//appel
if  (att_eval)	  action_ast_12();
  }
  private void regle13() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	T_Bloc x_3 = new T_Bloc(scanner ) ;
	S_SuiteAffectable_Bloc x_5 = new S_SuiteAffectable_Bloc(scanner,att_eval) ;
	//appel
if  (att_eval)	  action_auto_inh_13(x_3, x_5);
	x_2.analyser(LEX_Bloc.token_point);
	x_3.analyser(LEX_Bloc.token_identificateur);
if  (att_eval)	  action_inh_13(x_3, x_5);
	x_5.analyser() ;
if  (att_eval)	  action_ast_13(x_3, x_5);
  }
private void action_inh_13(T_Bloc x_3, S_SuiteAffectable_Bloc x_5) throws Exception {
try {
// instructions
x_5.att_support=this.att_factory.createFieldAssignment(this.att_support, x_3.att_txt);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#inh","SuiteAffectable -> point identificateur #inh SuiteAffectable1 #ast ;", e });
}
  }
private void action_auto_inh_13(T_Bloc x_3, S_SuiteAffectable_Bloc x_5) throws Exception {
try {
// instructions
x_5.att_factory=this.att_factory;
x_5.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","SuiteAffectable -> point identificateur #inh SuiteAffectable1 #ast ;", e });
}
  }
private void action_ast_12() throws Exception {
try {
// instructions
this.att_ast=this.att_support;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","SuiteAffectable -> #ast ;", e });
}
  }
private void action_ast_13(T_Bloc x_3, S_SuiteAffectable_Bloc x_5) throws Exception {
try {
// instructions
this.att_ast=x_5.att_ast;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","SuiteAffectable -> point identificateur #inh SuiteAffectable1 #ast ;", e });
}
  }
  public void analyser () throws Exception {
    scanner.lit ( 1 ) ;
    switch ( scanner.fenetre[0].code ) {
      case LEX_Bloc.token_affectation : // 40
        regle12 () ;
      break ;
      case LEX_Bloc.token_point : // 37
        regle13 () ;
      break ;
      default :
        	   scanner._interrompre(IProblem.Syntax, scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token,BlocMessages.Bloc_unexpected_token,new String[]{scanner.fenetre[0].getNom()});
    }
  }
  }
