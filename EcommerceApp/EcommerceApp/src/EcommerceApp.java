import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EcommerceApp {

    public static void main(String[] args) throws InterruptedException {

        long inicio = System.currentTimeMillis();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {

            executor.submit(() -> salvarPedido());
            executor.submit(() -> atualizarEstoque());
            executor.submit(() -> enviarEmail());
            executor.submit(() -> notificarMarketing());
            executor.submit(() -> gerarAuditoria());
        }

        long fim = System.currentTimeMillis();

        System.out.println("Fluxo concluido em " + (fim - inicio) + " ms.");
    }

    static void salvarPedido() {
        simularIO("Salvando pedido no Banco de dados");
    }

    static void atualizarEstoque() {
        simularIO("Atualizando estoque");
    }

    static void enviarEmail() {
        simularIO("Enviando Email");
    }

    static void notificarMarketing() {
        simularIO("Publicando evento Notificando Marketing");
    }

    static void gerarAuditoria() {
        simularIO("Gravando auditoria");
    }

    static void simularIO(String tarefa) {

        try {
            System.out.println(
                    Thread.currentThread() + " -> " + tarefa
            );

            Thread.sleep(2000);

            System.out.println(
                    Thread.currentThread() + " -> Finalizado."
            );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
