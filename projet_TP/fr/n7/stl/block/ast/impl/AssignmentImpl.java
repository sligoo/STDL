package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Instruction;
import fr.n7.stl.block.ast.VariableDeclaration;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for an array type.
 * @author Marc Pantel
 *
 */
public class AssignmentImpl implements Instruction {

	private VariableDeclaration declaration;
	private Expression value;
	private Expression assignable;
	private String name;

	/**
	 * Create an assignment instruction implementation from the assigned variable declaration
	 * and the assigned value.
	 * @param _declaration Assigned variable declaration.
	 * @param _value Assigned value.
	 */
	public AssignmentImpl(VariableDeclaration _declaration, Expression _value) {
		this.declaration = _declaration;
		this.value = _value;
	}

	/**
	 * Create an assignment instruction implementation from the assignable expression
	 * and the assigned value.
	 * @param _assignable Expression that can be assigned a value.
	 * @param _value Value assigned to the expression.
	 */
	public AssignmentImpl(Expression _assignable, Expression _value) {
		this.assignable = _assignable;
		this.value = _value;
	}

	public AssignmentImpl(String _name, Expression _value) {
		this.name = _name;
		this.value = _value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ((this.declaration != null)
				?this.declaration.getName()
				:this.assignable) /*:this.name)*/ + " = " + this.value.toString() + ";\n";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#checkType()
	 */
	@Override
	public boolean checkType() {
		return this.value.getType().compatibleWith(
				(this.declaration != null) ? this.declaration.getType() : this.assignable.getType());
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#allocateMemory(fr.n7.stl.tam.ast.Register, int)
	 */
	@Override
	public int allocateMemory(Register _register, int _offset) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = _factory.createFragment();

		fragment.append(value.getCode(_factory));

		if (this.declaration != null) {
			fragment.append(declaration.getCode(_factory));
		}
		else {
			fragment.append(assignable.getCode(_factory));
			fragment.add(_factory.createStoreI(this.value.getType().length()));
		}
		fragment.addComment(this.value.toString());
		return fragment;
	}

}
