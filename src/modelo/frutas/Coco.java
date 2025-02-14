package modelo.frutas;

import modelo.entidades.Fruta;
import modelo.utils.Efeitos;

import java.util.ArrayList;

public class Coco extends Fruta {
    public Coco(boolean bichada) {
        super(bichada, "Coco");
    }

    @Override
    public ArrayList<Efeitos> causarEfeito() {

        ArrayList<Efeitos> efeitos = new ArrayList<>();
        if (this.isBichada()){
            efeitos.add(Efeitos.ENVENENAMENTO);
        }
        efeitos.add(Efeitos.VELOCIDADE);
        return efeitos;
    }

}
