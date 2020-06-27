data class Site(val address: String, val foundationYear: Int)

fun makeReddit(): Site {
    return Site("reddit.com", 2005)
}