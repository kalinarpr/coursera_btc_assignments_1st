import java.util.ArrayList;

public class TxHandler {

    private UTXOPool ledger;

    /**
     * Creates a public ledger whose current UTXOPool (collection of unspent transaction outputs) is
     * {@code utxoPool}. This should make a copy of utxoPool by using the UTXOPool(UTXOPool uPool)
     * constructor.
     */
    public TxHandler(UTXOPool utxoPool) {
        // IMPLEMENT THIS
        this.ledger = new UTXOPool(utxoPool);

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

        //ArrayList<Transaction.Output> txOut = tx.getOutputs();
        ArrayList<Transaction.Input> txIn = tx.getInputs();

        //Verificando primeira condição (1)
        for (Transaction.Input in: txIn){
          UTXO nUtxo = new UTXO(in.prevTxHash,in.outputIndex);
          //Verifico se o input aponta para algum output dentro de UTXOPool.
          if (!this.ledger.contains(nUtxo)){
            return(txValid = false);
          }

          //Verificando segunda condição (2)
          Transaction.Output out = this.ledger.getTxOutput(nUtxo);
          if (!Crypto.verifySignature(out.address,tx.getRawTx(),in.signature)){
             return (txValid = false);
          }
        }

        //Verificando segunda condição
        // for (Transaction.Input in: txIn){
            UTXO nUtxo = new UTXO(in.prevTxHash,in.outputIndex);

        // }

        //Verificando terceira condição



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
