package kr.kro.uptune.Util

fun TrendReportReason(value : Int) : String
{
    var returnvalue = ""
    when(value) {
        1 -> returnvalue = "저작권 위반"
        2 -> returnvalue = "폭력적 또는 혐오적"
        3 -> returnvalue = "범죄 행위"
        4 -> returnvalue = "스팸"
    }
    return returnvalue;
}