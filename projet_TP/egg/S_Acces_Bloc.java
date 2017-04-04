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
  SymbolTable att_tds;
  boolean att_eval;
  Expression att_ast;
  LEX_Bloc att_scanner;
  Expression att_support;
  private void regle34() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	T_Bloc x_3 = new T_Bloc(scanner ) ;
	S_Acces_Bloc x_5 = new S_Acces_Bloc(scanner,att_eval) ;
	//appel
if  (att_eval)	  action_auto_inh_34(x_3, x_5);
	x_2.analyser(LEX_Bloc.token_point);
	x_3.analyser(LEX_Bloc.token_identificateur);
if  (att_eval)	  action_inh_34(x_3, x_5);
	x_5.analyser() ;
if  (att_eval)	  action_ast_34(x_3, x_5);
  }
  private void regle33() throws Exception {

	//declaration
	//appel
if  (att_eval)	  action_ast_33();
  }
  private void regle66() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	S_Expression_Bloc x_3 = new S_Expression_Bloc(scanner,att_eval) ;
	T_Bloc x_4 = new T_Bloc(scanner ) ;
	S_Acces_Bloc x_6 = new S_Acces_Bloc(scanner,att_eval) ;
	//appel
if  (att_eval)	  action_auto_inh_66(x_3, x_6);
	x_2.analyser(LEX_Bloc.token_crochet_ouvrant);
	x_3.analyser() ;
	x_4.analyser(LEX_Bloc.token_crochet_fermant);
if  (att_eval)	  action_inh_66(x_3, x_6);
	x_6.analyser() ;
if  (att_eval)	  action_ast_66(x_3, x_6);
  }
private void action_auto_inh_66(S_Expression_Bloc x_3, S_Acces_Bloc x_6) throws Exception {
try {
// instructions
x_3.att_factory=this.att_factory;
x_6.att_factory=this.att_factory;
x_3.att_tds=this.att_tds;
x_6.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Acces -> crochet_ouvrant Expression crochet_fermant #inh Acces1 #ast ;", e });
}
  }
private void action_auto_inh_34(T_Bloc x_3, S_Acces_Bloc x_5) throws Exception {
try {
// instructions
x_5.att_factory=this.att_factory;
x_5.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Acces -> point identificateur #inh Acces1 #ast ;", e });
}
  }
private void action_inh_34(T_Bloc x_3, S_Acces_Bloc x_5) throws Exception {
try {
// instructions
x_5.att_support=this.att_factory.createFieldAccess(this.att_support, x_3.att_txt);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#inh","Acces -> point identificateur #inh Acces1 #ast ;", e });
}
  }
private void action_inh_66(S_Expression_Bloc x_3, S_Acces_Bloc x_6) throws Exception {
try {
// instructions
x_6.att_support=this.att_factory.createArrayAccess(this.att_support, x_3.att_ast);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#inh","Acces -> crochet_ouvrant Expression crochet_fermant #inh Acces1 #ast ;", e });
}
  }
private void action_ast_33() throws Exception {
try {
// instructions
this.att_ast=this.att_support;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Acces -> #ast ;", e });
}
  }
private void action_ast_66(S_Expression_Bloc x_3, S_Acces_Bloc x_6) throws Exception {
try {
// instructions
this.att_ast=x_6.att_ast;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Acces -> crochet_ouvrant Expression crochet_fermant #inh Acces1 #ast ;", e });
}
  }
private void action_ast_34(T_Bloc x_3, S_Acces_Bloc x_5) throws Exception {
try {
// instructions
this.att_ast=x_5.att_ast;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Acces -> point identificateur #inh Acces1 #ast ;", e });
}
  }
  public void analyser () throws Exception {
    scanner.lit ( 1 ) ;
    switch ( scanner.fenetre[0].code ) {
      case LEX_Bloc.token_multiplication : // 49
        regle33 () ;
      break ;
      case LEX_Bloc.token_division : // 50
        regle33 () ;
      break ;
      case LEX_Bloc.token_modulo : // 51
        regle33 () ;
      break ;
      case LEX_Bloc.token_et : // 53
        regle33 () ;
      break ;
      case LEX_Bloc.token_addition : // 46
        regle33 () ;
      break ;
      case LEX_Bloc.token_soustraction : // 47
        regle33 () ;
      break ;
      case LEX_Bloc.token_ou : // 48
        regle33 () ;
      break ;
      case LEX_Bloc.token_egalite : // 45
        regle33 () ;
      break ;
      case LEX_Bloc.token_different : // 44
        regle33 () ;
      break ;
      case LEX_Bloc.token_inferieur : // 36
        regle33 () ;
      break ;
      case LEX_Bloc.token_inferieur_egal : // 38
        regle33 () ;
      break ;
      case LEX_Bloc.token_superieur : // 37
        regle33 () ;
      break ;
      case LEX_Bloc.token_superieur_egal : // 39
        regle33 () ;
      break ;
      case LEX_Bloc.token_crochet_fermant : // 33
        regle33 () ;
      break ;
      case LEX_Bloc.token_point_virgule : // 41
        regle33 () ;
      break ;
      case LEX_Bloc.token_parenthese_fermante : // 35
        regle33 () ;
      break ;
      case LEX_Bloc.token_virgule : // 42
        regle33 () ;
      break ;
      case LEX_Bloc.token_accolade_fermante : // 31
        regle33 () ;
      break ;
      case LEX_Bloc.token_point : // 40
        regle34 () ;
      break ;
      case LEX_Bloc.token_crochet_ouvrant : // 32
        regle66 () ;
      break ;
      default :
        	   scanner._interrompre(IProblem.Syntax, scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token,BlocMessages.Bloc_unexpected_token,new String[]{scanner.fenetre[0].getNom()});
    }
  }
  }
