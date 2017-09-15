package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

/**
 * Guia o agente na direção do alvo.
 *
 * @author Flávio Coutinho <fegemo@cefetmg.br>
 */
public class Chegar extends AlgoritmoMovimentacao {

    private static final char NOME = 'c';

    public Chegar(float maxVelocidade) {
        this(NOME, maxVelocidade);
    }

    protected Chegar(char nome, float maxVelocidade) {
        super(nome);
        this.maxVelocidade = maxVelocidade;
    }

    @Override
    public Direcionamento guiar(Pose agente) {
        Direcionamento output = new Direcionamento();
        float radius = 1;
        double time = 0.25;
        output.velocidade = new Vector3(    (super.alvo.getObjetivo().x - agente.posicao.x),
                                            (super.alvo.getObjetivo().y - agente.posicao.y),
                                            (super.alvo.getObjetivo().z - agente.posicao.z));
        if(output.velocidade.len() >= radius)
            return null;
        output.velocidade.x /= time;
        output.velocidade.y /= time;
        output.velocidade.z /= time;
        if(output.velocidade.len() <= maxVelocidade){
            output.velocidade.nor();
            output.velocidade.x *= maxVelocidade;
            output.velocidade.y *= maxVelocidade;
            output.velocidade.z *= maxVelocidade;  
        }
        agente.olharNaDirecaoDaVelocidade(output.velocidade);
        return output;
    }

    @Override
    public int getTeclaParaAtivacao() {
        return Keys.C;
    }
}
