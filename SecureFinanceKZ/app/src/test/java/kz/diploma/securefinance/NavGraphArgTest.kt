package kz.diploma.securefinance

import org.junit.Assert.*
import org.junit.Test
import org.w3c.dom.Document
import org.xml.sax.InputSource
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

/**
 * nav_graph.xml-де 'long' аргументтерінде defaultValue болмауын тексереді.
 * Navigation 2.7.7+: Long аргументіне integer defaultValue (-1) crash береді.
 */
class NavGraphArgTest {

    private val navGraphPath = "src/main/res/navigation/nav_graph.xml"

    @Test
    fun `nav_graph-та long аргументтерінде defaultValue болмауы керек`() {
        val file = File(navGraphPath)
        if (!file.exists()) return   // CI-де файл жолы басқаша болуы мүмкін

        val doc: Document = DocumentBuilderFactory.newInstance()
            .newDocumentBuilder()
            .parse(InputSource(file.inputStream()))

        val args = doc.getElementsByTagName("argument")
        for (i in 0 until args.length) {
            val node = args.item(i)
            val argType = node.attributes?.getNamedItem("app:argType")?.nodeValue ?: continue
            val defaultValue = node.attributes?.getNamedItem("android:defaultValue")?.nodeValue
            val name = node.attributes?.getNamedItem("android:name")?.nodeValue ?: "unknown"

            if (argType == "long") {
                assertNull(
                    "Navigation 2.7.7 long аргументіне integer defaultValue crash береді. " +
                    "Аргумент '$name' үшін defaultValue алып тасталуы керек.",
                    defaultValue
                )
            }
        }
    }

    @Test
    fun `nav_graph-та барлық fragment-тар name атрибутына ие болуы керек`() {
        val file = File(navGraphPath)
        if (!file.exists()) return

        val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
            .parse(InputSource(file.inputStream()))

        val fragments = doc.getElementsByTagName("fragment")
        for (i in 0 until fragments.length) {
            val node = fragments.item(i)
            val name = node.attributes?.getNamedItem("android:name")?.nodeValue
            val id = node.attributes?.getNamedItem("android:id")?.nodeValue ?: "unknown"
            assertNotNull("Fragment '$id' android:name атрибутынсыз", name)
            assertTrue("Fragment '$id' name бос болмауы керек", name!!.isNotBlank())
        }
    }
}
