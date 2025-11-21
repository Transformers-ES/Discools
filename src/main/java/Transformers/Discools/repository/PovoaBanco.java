import Transformers.Discools.model.DiscosCD;
import Transformers.Discools.model.Usuario;
import Transformers.Discools.repository.Banco;

public class PovoaBanco {

  private Banco banco;

  // ===================== DADOS INICIAIS =====================
  private void carregarDadosIniciais() {
    // Cria um usuário administrador
    Usuario administrador = new Usuario(1, "Admin", "admin@email.com",
        "1234", "Rua Central", true);
    banco.adicionarUsuario(administrador);

    // Cria um disco inicial
    DiscosCD discoExemplo = new DiscosCD(1, "this is what ____ feels like", "JVKE",
        "Pop", 42, 59.90f,
        "Um dos álbuns mais lindos da história.",
        10, "CD", "/midia/capa.jpg");
    banco.adicionarDisco(discoExemplo);
  }

}
