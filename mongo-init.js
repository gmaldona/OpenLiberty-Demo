db.createUser(
    {
        user: "root",
        pwd: "CHANGE THIS",
        roles: [
            {
                role: "readWrite",
                db: "csc480"
            }
        ]
    }
);