package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Assignable;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.FieldDeclaration;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author Marc Pantel
 *
 */
public class FieldAssignmentImpl extends FieldAccessImpl implements Assignable {

	/**
	 * @param _record
	 * @param _name
	 */
	public FieldAssignmentImpl(Expression _record, String _name) {
		super(_record, _name);
	}

	/**
	 * @param _record
	 * @param _field
	 */
	public FieldAssignmentImpl(Expression _record, FieldDeclaration _field) {
		super(_record, _field);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.impl.FieldAccessImpl#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = _factory.createFragment();

		Expression expression = this;
		int offset = 0;

		while (expression instanceof FieldAssignmentImpl){
			offset += ((FieldAssignmentImpl) expression).record.getType().length() -
					expression.getType().length() - ((FieldAssignmentImpl) expression).getPosition();
			expression = ((FieldAssignmentImpl) expression).record;
		}

		offset+=((VariableAssignmentImpl) expression).declaration.getOffset();
		fragment.add(_factory.createLoadA(((VariableAssignmentImpl) expression)
				.declaration.getRegister(), offset ));


		return fragment;
	}
	
}
