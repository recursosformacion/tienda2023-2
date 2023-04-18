package rf.com.tienda.servicio;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rf.com.tienda.dominio.Usuario;
import rf.com.tienda.exception.DomainException;
import rf.com.tienda.repository.UsuarioRepository;


@Service
public class UsuarioService implements IgenericoService<Usuario,Long> {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	public Usuario crear(Usuario usuario) {
		return usuarioRepo.save(usuario);
	}
	@Override
	public List<Usuario> leerTodos() {
		return (List<Usuario>) usuarioRepo.findAll();
	}

	@Override
	public Usuario actualizar(Usuario usuario, Long id_usuario) throws DomainException {

		Usuario userDb = usuarioRepo.findById(id_usuario).get();

		if (Objects.nonNull(usuario.getUser_nombre())
				&& !"".equalsIgnoreCase(usuario.getUser_nombre())) {
			userDb.setUser_nombre(usuario.getUser_nombre());
		}

		if (Objects.nonNull(usuario.getUser_email())
				&& !"".equalsIgnoreCase(usuario.getUser_email())) {
			userDb.setUser_email(usuario.getUser_email());
		}

		return usuarioRepo.save(userDb);
	}

	@Override
	public void borrar(Long id_usuario) {
		usuarioRepo.deleteById(id_usuario);

	}

	@Override
	public Optional<Usuario> leerPorId(Long id) {
		return usuarioRepo.findById(id);
	
	}
	

	
}