package Service;

import model.Cuenta;
import model.Transferencia;
import repository.TransferenciaRepository;

import java.util.Date;
import java.util.List;

public class ServicioBancario {
    private final TransferenciaRepository transferenciaRepo;

    public ServicioBancario(TransferenciaRepository transferenciaRepo) {
        this.transferenciaRepo = transferenciaRepo;
    }

    public void realizarTransferencia(Cuenta origen, Cuenta destino, double monto, Transferencia transferencia) {
        if (origen.getSaldo() >= monto) {
            origen.transferir(monto, destino);
            transferencia.setCuentaOrigen(origen);
            transferencia.setCuentaDestino(destino);
            transferencia.setMonto(monto);
            transferencia.setFecha(new Date());

            transferenciaRepo.guardarTransferencia(transferencia);
            System.out.println("Transferencia realizada con exito.");
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    public List<Transferencia> obtenerTransferenciasDeCuenta(int cuentaId) {
        return transferenciaRepo.mostrarTransaccionesDeCuenta(String.valueOf(cuentaId));
    }

    public void eliminarTransferencia(int transferenciaId) {
        transferenciaRepo.eliminarTransferencia(transferenciaId);
    }
}

