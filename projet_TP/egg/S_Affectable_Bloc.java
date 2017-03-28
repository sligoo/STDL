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
public class S_Affectable_Bloc {
LEX_Bloc scanner;
  S_Affectable_Bloc() {
	}
  S_Affectable_Bloc(LEX_Bloc scanner, boolean eval) {
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
  private void regle11() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	S_SuiteAffectable_Bloc x_4 = new S_SuiteAffectable_Bloc(scanner,att_eval) ;
	//appel
if  (att_eval)	  action_auto_inh_11(x_2, x_4);
	x_2.analyser(LEX_Bloc.token_identificateur);
if  (att_eval)	  action_inh_11(x_2, x_4);
	x_4.analyser() ;
if  (att_eval)	  action_ast_11(x_2, x_4);
  }
private void action_auto_inh_11(T_Bloc x_2, S_SuiteAffectable_Bloc x_4) throws Exception {
try {
// instructions
x_4.att_factory=this.att_factory;
x_4.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Affectable -> identificateur #inh SuiteAffectable #ast ;", e });
}
  }
private void action_inh_11(T_Bloc x_2, S_SuiteAffectable_Bloc x_4) throws Exception {
try {
// locales
Optional<Declaration> loc_o;
Declaration loc_d;
// instructions
if ((this.att_tds.knows(x_2.att_txt))){
loc_o=this.att_tds.get(x_2.att_txt);
loc_d=loc_o.get();
if (loc_d instanceof VariableDeclaration ){
x_4.att_support=this.att_factory.createVariableAssignment(((VariableDeclaration)loc_d));
}
else if (loc_d instanceof ConstantDeclaration ){
att_scanner._interrompre(IProblem.Semantic, att_scanner.getBeginLine(), IBlocMessages.id_BLOC_not_a_variable, BlocMessages.BLOC_not_a_variable,new Object[]{""+x_2.att_txt});

}

}
else {
att_scanner._interrompre(IProblem.Semantic, att_scanner.getBeginLine(), IBlocMessages.id_BLOC_undefined_ident, BlocMessages.BLOC_undefined_ident,new Object[]{""+x_2.att_txt});

}
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#inh","Affectable -> identificateur #inh SuiteAffectable #ast ;", e });
}
  }
private void action_ast_11(T_Bloc x_2, S_SuiteAffectable_Bloc x_4) throws Exception {
try {
// instructions
this.att_ast=x_4.att_ast;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Affectable -> identificateur #inh SuiteAffectable #ast ;", e });
}
  }
  public void analyser () throws Exception {
    regle11 () ;
  }
  }
