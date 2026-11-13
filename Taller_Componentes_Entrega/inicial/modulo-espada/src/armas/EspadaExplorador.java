package armas;

public class EspadaExplorador {
    private ArmaduraMarmol armaduraPegada = new ArmaduraMarmol();

    public int atacar() {
        return 50 - armaduraPegada.getPeso();
    }
}
