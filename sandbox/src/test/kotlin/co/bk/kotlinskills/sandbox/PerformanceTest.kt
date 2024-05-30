package co.bk.kotlinskills.sandbox

//import org.junit.Test
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class PerformanceTest {

    private val customizationExpandParameterMappingSet = setOf(
            "test1##value1", "test2##value2", "test3##value3", "test4##value4", "test5##value5",
            "test6##value6", "test7##value7", "test8##value8", "test9##value9", "test10##value10",
            "test11##value11", "test12##value12", "test13##value13", "test14##value14", "test15##value15",
            "test16##value16", "test17##value17", "test18##value18", "test19##value19", "test20##value20",
            "test21##value21", "test22##value22", "test23##value23", "test24##value24", "test25##value25",
            "test26##value26", "test27##value27", "test28##value28", "test29##value29", "test30##value30",
            "test31##value31", "test32##value32", "test33##value33", "test34##value34", "test35##value35",
            "test36##value36", "test37##value37", "test38##value38", "test39##value39", "test40##value40",
            "test41##value41", "test42##value42", "test43##value43", "test44##value44", "test45##value45",
            "test46##value46", "test47##value47", "test48##value48", "test49##value49", "test50##value50",
            "test51##value51", "test52##value52", "test53##value53", "test54##value54", "test55##value55",
            "test56##value56", "test57##value57", "test58##value58", "test59##value59", "test60##value60",
            "test61##value61", "test62##value62", "test63##value63", "test64##value64", "test65##value65",
            "test66##value66", "test67##value67", "test68##value68", "test69##value69", "test70##value70",
            "test71##value71", "test72##value72", "test73##value73", "test74##value74", "test75##value75",
            "test76##value76", "test77##value77", "test78##value78", "test79##value79", "test80##value80",
            "test81##value81", "test82##value82", "test83##value83", "test84##value84", "test85##value85",
            "test86##value86", "test87##value87", "test88##value88", "test89##value89", "test90##value90",
            "test91##value91", "test92##value92", "test93##value93", "test94##value94", "test95##value95",
            "test96##value96", "test97##value97", "test98##value98", "test99##value99", "test100##value100"
    )

    @Test
    fun testReducePerformance() {
        val time = measureTimeMillis {
            val apiFieldNames = customizationExpandParameterMappingSet
                    .filter { it.contains("##") }
                    .map { it.substringBefore("##")}
                    .reduce { acc, str -> "$acc,$str" }
            println("Reduce apiFieldNames: $apiFieldNames")
        }
        println("Reduce took $time milliseconds")
    }

    @Test
    fun testJoinToStringPerformance() {
        val time = measureTimeMillis {
            val apiFieldNames = customizationExpandParameterMappingSet
                    .filter { it.contains("##") }
                    .joinToString { it.substringBefore("##") }
            println("joinToString apiFieldNames: $apiFieldNames")
        }
        println("joinToString took $time milliseconds")
    }

    @Test
    fun testJoinToStringPerformanceUsingAsSequence() {
        val time = measureTimeMillis {
            val apiFieldNames = customizationExpandParameterMappingSet
                    .asSequence()
                    .filter { it.contains("##") }
                    .map { it.substringBefore("##")}
                    .reduce { acc, str -> "$acc,$str" }
            println("Reduce using asSequence apiFieldNames: $apiFieldNames")
        }
        println("Reduce using asSequence took $time milliseconds")
    }
}