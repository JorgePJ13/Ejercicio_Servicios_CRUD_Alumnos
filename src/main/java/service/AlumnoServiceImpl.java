package service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AlumnosDao;
import model.Alumno;


@Service
public class AlumnoServiceImpl implements AlumnoService {
	
	AlumnosDao al;

	public AlumnoServiceImpl(@Autowired AlumnosDao al) {
		super();
		this.al = al;
	}

	@Override
	public void altaAlumno(Alumno a) {
		al.save(a);

	}

	@Override
	public Alumno buscarAlumno(int idAlumno) {
		return al.findById(idAlumno).orElse(null);
	}

	@Override
	public boolean eliminarAlumno(int idAlumno) {
		if (buscarAlumno(idAlumno) != null) {
			al.deleteById(idAlumno);
			return true;
		}
		return false;
	}

	@Override
	public boolean actualizarAlumno(int idAlumno, String curso) {
		Alumno a = buscarAlumno(idAlumno);
		if (a != null) {
			a.setCurso(curso);
			al.save(a);
			return true;
		}
		return false;
	}

	@Override
	public List<Alumno> alumnos() {
		return al.findAll();
	}

	@Override
	public List<String> cursos() {
		return al.findAll().stream().map(a -> a.getCurso()).distinct().collect(Collectors.toList());
	}

}
