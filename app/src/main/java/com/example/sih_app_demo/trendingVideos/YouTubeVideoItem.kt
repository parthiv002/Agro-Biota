package com.example.sih_app_demo.trendingVideos

data class YouTubeVideoItem(
    val id: YouTubeVideoId,
    val snippet: YouTubeSnippet
)

data class YouTubeResponse(
    val items: List<YouTubeVideoItem>
)

data class YouTubeVideoId(
    val videoId: String
)

data class YouTubeSnippet(
    val title: String,
    val thumbnails: YouTubeThumbnails
)

data class YouTubeThumbnails(
    val high: YouTubeThumbnail
)

data class YouTubeThumbnail(
    val url: String
)

