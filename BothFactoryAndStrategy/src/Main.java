import br.com.aal.br.com.aal.application.usecase.ProcessOrderUseCase;
import br.com.aal.domain.model.Order;
import br.com.aal.infrastructure.factory.PaymentStrategyFactory;

import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        PaymentStrategyFactory factory = new PaymentStrategyFactory();
        ProcessOrderUseCase useCase = new ProcessOrderUseCase(factory);

        Order order = new Order("1", new BigDecimal("100.00"));

        useCase.execute("pix", order);
    }
}