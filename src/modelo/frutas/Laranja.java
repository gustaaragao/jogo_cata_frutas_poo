package modelo.frutas;

import modelo.entidades.Fruta;
import modelo.utils.Efeitos;

import java.util.ArrayList;

public class Laranja extends Fruta {
    public Laranja(boolean bichada) {
        super(bichada, "Laranja");
    }

    @Override
    public ArrayList<Efeitos> causarEfeito() {

        ArrayList<Efeitos> efeitos = new ArrayList<>();
        if (this.isBichada()) efeitos.add(Efeitos.ENVENENAMENTO);
        else efeitos.add(Efeitos.ANTIDOTO);
        return efeitos;
    }

}
