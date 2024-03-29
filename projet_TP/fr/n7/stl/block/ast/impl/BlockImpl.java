package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the Abstract Syntax Tree node for an instruction block.
 * @author Marc Pantel
 *
 */
public class BlockImpl implements Block {

	/**
	 * Sequence of instructions contained in a block.
	 */
	private List<Instruction> instructions;

	/**
	 * Hierarchical structure of blocks.
	 * Link to the container block.
	 * 
	 */
	private Optional<Block> context;
	
	/**
	 * Subset of instructions corresponding to variable declarations in the same order.
	 */
	private List<VariableDeclaration> variables;

	/**
	 * Subset of instructions corresponding to constant declarations in the same order.
	 */	
	private List<ConstantDeclaration> constants;

	/**
	 * Subset of instructions corresponding to type declarations in the same order.
	 */
	private List<TypeDeclaration> types;

	/**
	 * offset of the block
	 */
	private int offset;

	/**
	 * Constructor for a block contained in a _context block.
	 * @param _context Englobing block.
	 */
	public BlockImpl(Block _context) {
		assert( _context != null);
		this.instructions = new LinkedList<>();
		this.variables = new LinkedList<>();
		this.constants = new LinkedList<>();
		this.types = new LinkedList<>();
		if (_context == null) {
			this.context = Optional.empty();
		} else {
			this.context = Optional.of(_context);
		}
	}
	
	/**
	 * Constructor for a block root of the block hierarchy.
	 */
	public BlockImpl() {
		this.instructions = new LinkedList<>();
		this.variables = new LinkedList<>();
		this.constants = new LinkedList<>();
		this.types = new LinkedList<>();
		this.context = Optional.empty();
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.Block#add(fr.n7.block.ast.Instruction)
	 */
	@Override
	public void add(Instruction _instruction) {
		this.instructions.add(_instruction);
		if (_instruction instanceof Declaration) {
			this.register((Declaration)_instruction);
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Scope#register(fr.n7.stl.block.ast.Declaration)
	 */
	public void register(Declaration _declaration) {
		if (_declaration instanceof VariableDeclaration) {
			this.variables.add((VariableDeclaration)_declaration);
		} else {
			if (_declaration instanceof ConstantDeclaration) {
				this.constants.add((ConstantDeclaration) _declaration);
			} else {
				if (_declaration instanceof TypeDeclaration) {
					this.types.add((TypeDeclaration) _declaration);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.HierarchicalScope#knows(java.lang.String)
	 */
	@Override
	public boolean knows(String _name) {
		if (this.contains(_name)) {
			return true;
		} else {
			return context.map(block -> block.knows(_name)).orElse(false);
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Scope#get(java.lang.String)
	 */
	@Override
	public Optional<Declaration> get(String _name) {
		for (Declaration _declaration : this.variables) {
			if (_declaration.getName().equals(_name)) {
				return Optional.of(_declaration);
			}
		}
		for (Declaration _declaration : this.constants) {
			if (_declaration.getName().equals(_name)) {
				return Optional.of(_declaration);
			}
		}
		for (Declaration _declaration : this.types) {
			if (_declaration.getName().equals(_name)) {
				return Optional.of(_declaration);
			}
		}
		return Optional.empty();
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Scope#contains(java.lang.String)
	 */
	@Override
	public boolean contains(String _name) {
		for (Declaration _declaration : this.variables) {
			if (_declaration.getName().contentEquals(_name)) {
				return true;
			}
		}
		for (Declaration _declaration : this.constants) {
			if (_declaration.getName().contentEquals(_name)) {
				return true;
			}
		}
		for (Declaration _declaration : this.types) {
			if (_declaration.getName().contentEquals(_name)) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Scope#accepts(fr.n7.stl.block.ast.Declaration)
	 */
	@Override
	public boolean accepts(Declaration _declaration) {
		return (! this.contains(_declaration.getName()));
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Block#addAll(java.lang.Iterable)
	 */
	@Override
	public void addAll(Iterable<Instruction> _instructions) {
		for (Instruction i : _instructions) {
			this.instructions.add(i);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder _local = new StringBuilder();
		for (Instruction _instruction : this.instructions) {
			_local.append(_instruction);
		}
		return "{\n" + _local + "}\n" ;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Block#checkType()
	 */
	@Override
	public boolean checkType() {
		boolean _result = true;
		for (Instruction _instruction : this.instructions) {
			_result = _result && _instruction.checkType();
		}
		return _result;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Block#allocateMemory(fr.n7.stl.tam.ast.Register, int)
	 */
	@Override
	public int allocateMemory(Register _register, int _offset) {
		int dep = _offset;
		for (Instruction i: this.instructions){
			dep += i.allocateMemory(_register, dep);
		}
		this.offset = dep - offset;
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Block#generateCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = _factory.createFragment();
		for (Instruction i : this.instructions) {
			fragment.append(i.getCode(_factory));
		}
		fragment.add(_factory.createPop(0, this.offset));
		return fragment;
	}

}
