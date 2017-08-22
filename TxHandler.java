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
        //Verificando primeira condição (1)
        ArrayList<Transaction.Output> txOut = tx.getOutputs();
        ArrayList<Transaction.Input> txIn = tx.getInputs();
        //ArrayList<UTXO> utxos = this.ledger.getAllUTXO();
        for (int i = 0; i < txOut.length; i++){
          //PAREI AQUI
          UTXO nUtxo = new UTXO(tx.getHash(),i);
          if (!this.ledger.contains(nUtxo)){
            return(txValid = false);
          }
        }

        //Verificando segunda condição
        for (Transaction.Input in: txIn){
            if (!Crypto.verifySignature(txOut[in.outputIndex],in.signature)){
              return (txValid = false);
            }
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
