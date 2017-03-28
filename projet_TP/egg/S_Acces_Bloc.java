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
public class S_Acces_Bloc {
LEX_Bloc scanner;
  S_Acces_Bloc() {
	}
  S_Acces_Bloc(LEX_Bloc scanner, boolean eval) {
	this.scanner = scanner;
	this.att_eval = eval;
	this.att_scanner = scanner;
	}
int [] sync= new int[0];
  BlockFactory att_factory;
  boolean att_eval;
  Expression att_ast;
  LEX_Bloc att_scanner;
  Expression att_support;
  private void regle35() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	T_Bloc x_3 = new T_Bloc(scanner ) ;
	S_Acces_Bloc x_5 = new S_Acces_Bloc(scanner,att_eval) ;
	//appel
if  (att_eval)	  action_auto_inh_35(x_3, x_5);
	x_2.analyser(LEX_Bloc.token_point);
	x_3.analyser(LEX_Bloc.token_identificateur);
if  (att_eval)	  action_inh_35(x_3, x_5);
	x_5.analyser() ;
if  (att_eval)	  action_ast_35(x_3, x_5);
  }
  private void regle34() throws Exception {

	//declaration
	//appel
if  (att_eval)	  action_ast_34();
  }
private void action_inh_35(T_Bloc x_3, S_Acces_Bloc x_5) throws Exception {
try {
// instructions
x_5.att_support=this.att_factory.createFieldAccess(this.att_support, x_3.att_txt);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#inh","Acces -> point identificateur #inh Acces1 #ast ;", e });
}
  }
private void action_auto_inh_35(T_Bloc x_3, S_Acces_Bloc x_5) throws Exception {
try {
// instructions
x_5.att_factory=this.att_factory;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Acces -> point identificateur #inh Acces1 #ast ;", e });
}
  }
private void action_ast_34() throws Exception {
try {
// instructions
this.att_ast=this.att_support;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Acces -> #ast ;", e });
}
  }
private void action_ast_35(T_Bloc x_3, S_Acces_Bloc x_5) throws Exception {
try {
// instructions
this.att_ast=x_5.att_ast;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Acces -> point identificateur #inh Acces1 #ast ;", e });
}
  }
  public void analyser () throws Exception {
    scanner.lit ( 1 ) ;
    switch ( scanner.fenetre[0].code ) {
      case LEX_Bloc.token_multiplication : // 46
        regle34 () ;
      break ;
      case LEX_Bloc.token_division : // 47
        regle34 () ;
      break ;
      case LEX_Bloc.token_modulo : // 48
        regle34 () ;
      break ;
      case LEX_Bloc.token_et : // 50
        regle34 () ;
      break ;
      case LEX_Bloc.token_addition : // 43
        regle34 () ;
      break ;
      case LEX_Bloc.token_soustraction : // 44
        regle34 () ;
      break ;
      case LEX_Bloc.token_ou : // 45
        regle34 () ;
      break ;
      case LEX_Bloc.token_egalite : // 42
        regle34 () ;
      break ;
      case LEX_Bloc.token_different : // 41
        regle34 () ;
      break ;
      case LEX_Bloc.token_inferieur : // 33
        regle34 () ;
      break ;
      case LEX_Bloc.token_inferieur_egal : // 35
        regle34 () ;
      break ;
      case LEX_Bloc.token_superieur : // 34
        regle34 () ;
      break ;
      case LEX_Bloc.token_superieur_egal : // 36
        regle34 () ;
      break ;
      case LEX_Bloc.token_point_virgule : // 38
        regle34 () ;
      break ;
      case LEX_Bloc.token_parenthese_fermante : // 32
        regle34 () ;
      break ;
      case LEX_Bloc.token_virgule : // 39
        regle34 () ;
      break ;
      case LEX_Bloc.token_accolade_fermante : // 28
        regle34 () ;
      break ;
      case LEX_Bloc.token_point : // 37
        regle35 () ;
      break ;
      default :
        	   scanner._interrompre(IProblem.Syntax, scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token,BlocMessages.Bloc_unexpected_token,new String[]{scanner.fenetre[0].getNom()});
    }
  }
  }
