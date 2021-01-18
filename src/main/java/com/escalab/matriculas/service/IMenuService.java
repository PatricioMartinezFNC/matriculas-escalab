package com.escalab.matriculas.service;

import java.util.List;

import com.escalab.matriculas.model.Menu;

public interface IMenuService extends ICRUD<Menu> {
	
	List<Menu> listarMenuPorUsuario(String nombre);

}
