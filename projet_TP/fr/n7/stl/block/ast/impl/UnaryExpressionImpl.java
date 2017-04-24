package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.CoupleType;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.block.ast.UnaryOperator;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for an unary operation expression.
 * @author Marc Pantel
 *
 */
public class UnaryExpressionImpl implements Expression {

	private UnaryOperator operator;
	private Expression parameter;
	
	/**
	 * Builds a unary expression Abstract Syntax Tree node from the parameter sub-expression
	 * and the unary operation.
	 * @param _operator : Unary Operator.
	 * @param _parameter : Expression for the parameter.
	 */
	public UnaryExpressionImpl(UnaryOperator _operator, Expression _parameter) {
		this.operator = _operator;
		this.parameter = _parameter;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + this.operator + " " + this.parameter + ")";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		Type resultType = this.parameter.getType();
		switch (this.operator) {
			case Negate: {
				if (resultType.compatibleWith(AtomicType.BooleanType))  {
					return resultType;
				} else {
					return AtomicType.ErrorType;
				}
			}
			case Opposite: {
				if (resultType.compatibleWith(AtomicType.FloatingType)) {
					return resultType;
				} else {
					return AtomicType.ErrorType;
				}
			}
			case First: {
				if (resultType instanceof CoupleType) {
					return ((CoupleType)resultType).getFirst();
				} else {
					return AtomicType.ErrorType;
				}
			}
			case Second: {
				if (resultType instanceof CoupleType) {
					return ((CoupleType)resultType).getFirst();
				} else {
					return AtomicType.ErrorType;
				}
			}
			default : return AtomicType.ErrorType;
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = this.parameter.getCode(_factory);
		int first, second;
		switch (this.operator) {
			case Negate: case Opposite:
				fragment.add(TAMFactory.createUnaryOperator(this.operator));
				break;
			case First:
				second = ((CoupleType)this.parameter.getType()).getSecond().length();
				fragment.add(_factory.createPop(0, second));
				break;
			case Second:
				first = ((CoupleType)this.parameter.getType()).getFirst().length();
				second = ((CoupleType)this.parameter.getType()).getSecond().length();
				fragment.add(_factory.createPop(second, first));
				break;
			default:
				throw new SemanticsUndefinedException("Unary operator " + this.operator +
						" is not defined");
		}
		return fragment;
	}

}
