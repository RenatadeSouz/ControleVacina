package com.example.demo.repository;

import com.example.demo.config.Conexao;
import com.example.demo.domain.Usuario;
import com.example.demo.dto.LoginDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UsuarioRepository {

    public Usuario findByAccess(LoginDto login) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        Usuario usuario = null;

        String query = "SELECT * FROM USUARIO WHERE LOGIN = ? AND SENHA = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, login.getLogin());
            ps.setString(2, login.getSenha());

            ResultSet resultado = ps.executeQuery();
            if (resultado.next()) {
                usuario = new Usuario();
                usuario.setId(resultado.getLong("id"));
                usuario.setLogin(resultado.getString("login"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setCargo(resultado.getString("cargo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Usuario não encontrado");
        } finally {
            conexao.desconectar(conn);
        }
        return usuario;
    }
}
