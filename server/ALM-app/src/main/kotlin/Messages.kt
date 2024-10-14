import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import global.genesis.gen.dao.LoanTrade
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

/**
 * Note:
 * We can avoid having to define all these classes by swapping the external app for a simple spring boot app
 * Then we can generate an OpenAPI spec and use the genesis open api plugin to generate these types for us
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
data class LoanMessage(
    @JsonProperty("LOAN_ID")
    val loanId: String,
    @JsonProperty("CLIENT_NAME")
    val clientName: String,
    @JsonProperty("FACILITY_NAME")
    val facilityName: String,
    @JsonProperty("FACILITY_AMOUNT")
    val facilityAmount: Double,
    @JsonProperty("FACILITY_CCY")
    val facilityCurrency: String,
    @JsonProperty("DRAWDOWN_DATE")
    val drawdownDate: Long,
    @JsonProperty("DRAWDOWN_AMOUNT")
    val drawdownAmount: Double,
    @JsonProperty("DRAWDOWN_CURRENCY")
    val drawdownCurrency: String,
    @JsonProperty("PAYMENT_DATE")
    val paymentDate: Long,
    @JsonProperty("PAYMENT_CURRENCY")
    val paymentCurrency: String,
    @JsonProperty("PAYMENT_AMOUNT")
    val paymentAmount: Double
)

data class AllLoansResponse(
    @JsonProperty("ROWS_COUNT")
    val rowsCount: Int,
    @JsonProperty("MESSAGE_TYPE")
    val messageType: String = "EVENT_LOGIN_AUTH",
    @JsonProperty("ROW")
    val row: List<LoanMessage>,
    @JsonProperty("MORE_ROWS")
    val moreRows: Boolean,
    @JsonProperty("SOURCE_REF")
    val sourceRef: String,
    @JsonProperty("SEQUENCE_ID")
    val sequenceId: Int
)

data class DataLogonDetails(
    @JsonProperty("MAX_ROWS")
    val maxRows: Int,
    @JsonProperty("MAX_VIEW")
    val maxView: Int,
)

data class DataLogonRequest(
    @JsonProperty("DETAILS")
    val details: DataLogonDetails
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class LoginResponse(
    @JsonProperty("SESSION_AUTH_TOKEN")
    val sessionAuthToken: String,
)

data class Details(
    @JsonProperty("USER_NAME")
    val username: String = "JaneDee",
    @JsonProperty("PASSWORD")
    val password: String = "beONneON*74"
)

data class LoginRequest(
    @JsonProperty("SOURCE_REF")
    val sourceRef: String = "login-1",
    @JsonProperty("MESSAGE_TYPE")
    val messageType: String = "EVENT_LOGIN_AUTH",
    @JsonProperty("DETAILS")
    val details: Details = Details()
)

fun LoanMessage.asLoanTrade() = LoanTrade(
    clientName = clientName,
    drawdownAmount = drawdownAmount,
    drawdownCurrency = drawdownCurrency,
    drawdownDate = unixTimestampToDateTime(drawdownDate),
    facilityAmount = facilityAmount,
    facilityCurrency = facilityCurrency,
    facilityName = facilityName,
    loanId = loanId,
    paymentAmount = paymentAmount,
    paymentCurrency = paymentCurrency,
    paymentDate = unixTimestampToDateTime(paymentDate)
)

private fun unixTimestampToDateTime(timestamp: Long) =
    DateTime(timestamp * 1000L, DateTimeZone.UTC)

data object Message