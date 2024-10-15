-- **************************************************************
-- This script destroys the database and associated users
-- **************************************************************

-- The following line terminates any active connections to the database so that it can be destroyed
SELECT pg_terminate_backend(pid)
FROM pg_stat_activity
WHERE datname = 'ale_atlas';

DROP DATABASE ale_atlas;

DROP USER ale_atlas_owner;
DROP USER ale_atlas_appuser;
