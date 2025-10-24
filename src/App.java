import DAO.Conexao;
import View.MenuView;

void main() {
    Conexao conexao  = new Conexao();

    conexao.conectar();
    MenuView.menuExibir();
}

