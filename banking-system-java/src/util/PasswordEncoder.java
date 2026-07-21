package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {

    /**
     * Gera o hash da senha utilizando BCrypt.
     *
     * @param senha Senha digitada pelo usuário.
     * @return Hash da senha.
     */
    public static String gerarHash(String senha) {

        return BCrypt.hashpw(senha, BCrypt.gensalt());

    }

    /**
     * Verifica se a senha digitada corresponde ao hash salvo.
     *
     * @param senhaDigitada Senha informada pelo usuário.
     * @param hashSalvo Hash armazenado no banco de dados.
     * @return true se a senha estiver correta, false caso contrário.
     */
    public static boolean verificarSenha(String senhaDigitada,
                                         String hashSalvo) {

        return BCrypt.checkpw(senhaDigitada, hashSalvo);

    }

}