package kr.kro.uptune.Util

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

fun isCorrectSession(session: HttpSession): Boolean
{
    if(session.getAttribute("type") == "register" || session.getAttribute("type") == "utuc" || session.getAttribute("type") == null)
    {
        return false;
    }
    return true;
}