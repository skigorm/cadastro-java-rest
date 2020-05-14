package br.com.cadastro.interceptor;

import br.com.cadastro.annotation.Transactional;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

@Interceptor
@Transactional
public class TransactionalInterceptor implements Serializable {
    @Inject
    private EntityManager em;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception{
        EntityTransaction transaction = em.getTransaction();
        Object  result = null;
        try {
            if(!transaction.isActive()){
                transaction.begin();
                result = context.proceed();
                if(!transaction.getRollbackOnly())
                    transaction.commit();
                else transaction.rollback();
            }
        } catch (Exception e){
            e.printStackTrace();
            if(transaction.isActive()){
                transaction.rollback();
                System.out.println("Transacao nao efetuada, roolback executado");
            }
        }
        return result;
    }
}
