/**
 Sync function sample. To be enhanced ...
 Warn : to not be confused with Sync Function API reference channel(), we name a thread of discussion threadChannel.
 Here we don't introduce the concept of messages to reduce complexity
 Must be refactor !
*/
function (doc, oldDoc, meta) {

    if (doc.type == "user-details"){

        requireUser(doc.email) // throw error if it doesn't concern the current user

        // Create a personal user channel
        var userChannel = doc.email
        channel(userChannel)
        access(doc.email, userChannel)

        // Publisher : route and grant access
        for (var idx in doc.publishers){
            var channelId = "publisher_" + doc.publishers[idx] // Apply a naming rule based on the role on the precise thread channel
            access(doc.email, channelId) // Give access to the current user to this specific channel
        }

        // React_only : route and grant access
        for (var idx in doc.read_only){
            var channelId = "read_only" + doc.read_only[idx]
            access(doc.email, channelId)
        }

        // Read_only : route and grant access
        for (var idx in doc.react_only){
            var channelId = "react_only" + doc.react_only[idx]
            access(doc.email, channelId)
        }
    }
    else if (doc.type == "thread-channel"){

        // Always create 3 channels per thread channel
        var publisherChannel = "publisher_" + doc._id
        var readChannel = "read_only" + doc._id
        var reactChannel = "react_only_" + doc._id

        // Route
        channel(publisherChannel)
        channel(readChannel)
        channel(reactChannel)

        // Update of a doc is just allowed for publisher and react_only
        // Read can't be update because an error is thrown
        requireAccess(["publisher_" + doc._id, "react_only_" + doc._id])

        // handle react_only thread_channel with old_doc
    }
}