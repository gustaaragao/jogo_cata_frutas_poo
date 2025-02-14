package modelo.frutas;

import modelo.entidades.Fruta;
import modelo.utils.Efeitos;

import java.util.ArrayList;

public class Abacate extends Fruta {

    public Abacate(boolean bichada) {
        super(bichada, "Abacate");
    }

    @Override
    public ArrayList<Efeitos> causarEfeito() {

        ArrayList<Efeitos> efeitos = new ArrayList<>();
        if (this.isBichada()){
            efeitos.add(Efeitos.ENVENENAMENTO);
        }
        efeitos.add(Efeitos.FORCA);
        return efeitos;
    }
}
