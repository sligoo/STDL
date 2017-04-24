package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.FieldDeclaration;
import fr.n7.stl.block.ast.RecordType;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

import java.util.Collections;
import java.util.Optional;
import java.util.List;

/**
 * Implementation of the Abstract Syntax Tree node for accessing a field in a record.
 * @author Marc Pantel
 *
 */

public class FieldAccessImpl implements Expression {

	Expression record;
	private String name;
	private FieldDeclaration field;

	public FieldAccessImpl(Expression _record, String _name) {
		this.record = _record;
		this.name = _name;
	}

	public FieldAccessImpl(Expression _record, FieldDeclaration _field) {
		this.record = _record;
		this.field = _field;
		this.name = this.field.getName();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.record + "." + this.name;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		if (field == null) {
			Optional<FieldDeclaration> field = ((RecordTypeImpl)this.record.getType()).get(name);
			if (field.isPresent()) {
				return field.get().getType();
			}
		}
		return this.field.getType();
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = _factory.createFragment();

		int length = this.getField().getType().length();
		fragment.append(this.record.getCode(_factory));
		fragment.add(_factory.createPop(0, this.getPosition())); //POP(0)
		fragment.add(_factory.createPop(length, this.record.getType().length() - length -
				this.getPosition())); //POP(taille du champ souhaité)

		fragment.addComment("Access au field "  + this.getPosition() + " de " + this.name);

		return fragment;


	}

	protected FieldDeclaration getField(){
		if (field == null){
			Optional<FieldDeclaration> field = ((RecordTypeImpl)this.record.getType()).get(name);
			if (field.isPresent()) {
				return field.get();
			}
		}
		return field;
	}

	protected int getPosition() {
		int precLength = 0;
		boolean seen = false;
		List<FieldDeclaration> fields = ((RecordType)this.record.getType()).getFields();
		Collections.reverse(fields);
		for (FieldDeclaration f : fields) {
			seen |= this.getField().getName().equals(f.getName());
			if (!seen) {
				precLength += f.getType().length();
			}
		}
		return precLength;
	}


}
