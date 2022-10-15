package ai.ftech.travelluxury.ui.payment

interface PaymentContract {

    interface View {
        fun onPaymentSuccess()
        fun onPaymentFail()
    }

    interface Presenter {
        fun handlePayment()
    }

}
