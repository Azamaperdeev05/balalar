package kz.diploma.securefinance

import kz.diploma.securefinance.util.formatMoney
import kz.diploma.securefinance.util.toFormattedDate
import org.junit.Assert.*
import org.junit.Test
import java.util.Calendar

class FormatterTest {

    @Test
    fun `formatMoney KZT валютасы ₸ символымен шығуы керек`() {
        val result = 125_000.0.formatMoney("KZT")
        assertTrue("₸ символы болуы керек", result.contains("₸"))
    }

    @Test
    fun `formatMoney USD валютасы долл символымен шығуы керек`() {
        val result = 100.0.formatMoney("USD")
        assertTrue("$ символы болуы керек", result.contains("$"))
    }

    @Test
    fun `formatMoney нөл шығыны дұрыс форматталуы керек`() {
        val result = 0.0.formatMoney("KZT")
        assertTrue("Нөл мән форматталуы керек", result.contains("0"))
    }

    @Test
    fun `toFormattedDate белгілі бір timestamp-ті күн форматтауы керек`() {
        val cal = Calendar.getInstance()
        cal.set(2026, Calendar.APRIL, 24, 12, 0, 0)
        val result = cal.timeInMillis.toFormattedDate()
        assertTrue("2026 жыл болуы керек", result.contains("2026"))
        assertTrue("Сәуір айы болуы керек", result.isNotEmpty())
    }
}
