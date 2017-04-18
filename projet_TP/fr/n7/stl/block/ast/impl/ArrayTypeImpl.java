package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ArrayType;
import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.Type;
/**
 * @author Marc Pantel
 *
 */
public class ArrayTypeImpl implements Type, ArrayType {

	private Type element;

	public ArrayTypeImpl(Type _element) {
		this.element = _element;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#equalsTo(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean equalsTo(Type _other) {
		return _other instanceof ArrayTypeImpl && this.element.equalsTo(((ArrayTypeImpl)
				_other).element);

	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#compatibleWith(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean compatibleWith(Type _other) {
		return _other instanceof ArrayTypeImpl && this.element.compatibleWith((
				(ArrayTypeImpl) _other).element);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#merge(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public Type merge(Type _other) {
		if (_other instanceof ArrayTypeImpl) {
			return new ArrayTypeImpl(this.element.merge(((ArrayTypeImpl)_other).element));
		}
		return AtomicType.ErrorType;

	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#length(int)
	 */
	@Override
	public int length() {
		return this.element.length();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.element + "[]";
	}

	/**
	 * @return Type of the elements in the array.
	 */
	public Type getType() {
		return this;
	}

	@Override
	public Type getPointedType() {
		return this.element;
	}

}
