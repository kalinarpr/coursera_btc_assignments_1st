import java.util.ArrayList;

public class TxHandler {

    private UTXOPool utxoPool;

    /**
     * Creates a public ledger whose current UTXOPool (collection of unspent transaction outputs) is
     * {@code utxoPool}. This should make a copy of utxoPool by using the UTXOPool(UTXOPool uPool)
     * constructor.
     */
    public TxHandler(UTXOPool utxoPool) {
        // IMPLEMENT THIS
        this.utxoPool = new UTXOPool(utxoPool);

    }

    /**
     * @return true if:
     * (1) all outputs claimed by {@code tx} are in the current UTXO pool,
     * (2) the signatures on each input of {@code tx} are valid,
     * (3) no UTXO is claimed multiple times by {@code tx},
     * (4) all of {@code tx}s output values are non-negative, and
     * (5) the sum of {@code tx}s input values is greater than or equal to the sum of its output
     *     values; and false otherwise.
     */
    public boolean isValidTx(Transaction tx) {
        // IMPLEMENT THIS
        boolean txValid = true;
        double inSum = 0.0;
        double outSum = 0.0;

        ArrayList<Transaction.Input> txIn = tx.getInputs();
        for (Transaction.Input in: txIn){
          //Verificando primeira condição (1)
          //Primeiro verifico se todos os inputs desbloqueiam algum output
          //dentro do UTXOPool.
          UTXO utxo = new UTXO(in.prevTxHash,in.outputIndex);
          if (!this.utxoPool.contains(utxo)){
            return(txValid = false);
          }

          //Verificando segunda condição (2)
          Transaction.Output out = this.ledger.getTxOutput(nUtxo);
          if (!Crypto.verifySignature(out.address,tx.getRawTx(),in.signature)){
             return (txValid = false);
          }
          //Somando os valores de input para verificar condição 5.
          inSum += out.value;
        }

        //Verificando a terceira condição.
        
        //Verificando quarta condição (4)
        ArrayList<Transaction.Output> txOut = tx.getOutputs();
        for (Transaction.Output out: txOut){
          if (out.value < 0){
            return (txValid = false);
          }
          outSum += out.value;
        }

        //verificando a quinta condição (5)
        if (inSum < outSum){
          return (txValid = false)
        }

        return txValid;
    }

    /**
     * Handles each epoch by receiving an unordered array of proposed transactions, checking each
     * transaction for correctness, returning a mutually valid array of accepted transactions, and
     * updating the current UTXO pool as appropriate.
     */
    public Transaction[] handleTxs(Transaction[] possibleTxs) {
        // IMPLEMENT THIS
    }

}
