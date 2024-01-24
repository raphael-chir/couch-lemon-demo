/**
 Sync function sample. To be enhanced ...
 Warn : to not be confused with Sync Function API reference channel(), we name a thread of discussion threadChannel.
 Here we don't introduce the concept of messages to reduce complexity
 Must be refactor !
*/
function (doc, oldDoc, meta) {

    if (doc.type == "user-details"){

        requireUser(doc.user) // throw error if it doesn't concern the current user

        // Create a personal user channel
        var userChannel = doc.email
        channel(userChannel)
        access(doc.email, userChannel)

        // Publisher : route and grant access
        for (var threadChannelId in doc.publishers){
            var channelId = "publisher_" + threadChannelId // Apply a naming rule based on the role on the precise thread channel
            access(doc.email, channelId) // Give access to the current user to this specific channel
        }

        // React_only : route and grant access
        for (var threadChannelId in doc.read_only){
            var channelId = "read_only" + threadChannelId
            access(doc.email, channelId)
        }

        // Read_only : route and grant access
        for (var threadChannelId in doc.react_only){
            var channelId = "react_only" + threadChannelId
            access(doc.email, channelId)
        }
    }
    else if (doc.type == "thread-channel"){

        // Always create 3 channels per thread channel
        var publisher-channelId = "publisher_" + doc.threadChannelId
        var read-only-channelId = "read_only" + doc.threadChannelId
        var react-only-channelId = "react_only_" + doc.threadChannelId

        // Route
        channel(publisher-channelId)
        channel(read-only-channelId)
        channel(react-only-channelId)

        // Update of a doc is just allowed for publisher and react_only
        // Read can't be update because an error is thrown
        requireAccess(["publisher_" + doc.threadChannelId, "react_only_" + doc.threadChannelId])

        // handle react_only thread_channel with old_doc
    }
}